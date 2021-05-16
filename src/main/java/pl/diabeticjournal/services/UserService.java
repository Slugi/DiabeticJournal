package pl.diabeticjournal.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.diabeticjournal.entity.Token;
import pl.diabeticjournal.entity.User;
import pl.diabeticjournal.repository.TokenRepository;
import pl.diabeticjournal.repository.UserRepository;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final MailService mailService;

    public void registerUser(User user, String url) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserName(user.getUsername());
        userRepo.save(user);
        sendToken(user, url);
    }

    private void sendToken(User user, String url) {
        String tokenValue = UUID.randomUUID().toString();
        String verifyMessage = "Kliknij w podany link, aby potwierdzić rejestrację";
        Token token = new Token();
        token.setTokenValue(tokenValue);
        token.setUser(user);
        tokenRepository.save(token);
        String activationURL = url + "/token?value=" + tokenValue;
        try {
            mailService.sendMail(
                    user.getEmail(), "Potwierdzenie rejestracji!", verifyMessage + "\n" + activationURL, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public boolean isEmailexists(User user) {
        return userRepo.findUserByEmail(user.getEmail()).isPresent();
    }

    public boolean isUserNameExists(User user) {
        return userRepo.findByUserName(user.getUsername()).isPresent();
    }

    public User getUserByName(String name) {

        return userRepo
                .findByUserName(name)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono użytkownika."));
    }

    public void activate(User user) {
        user.setEnabled(true);
        userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        return userRepo.findUserById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono użytkownika."));
    }

    public void userDelete(User user) {
        userRepo.delete(user);
    }
}
