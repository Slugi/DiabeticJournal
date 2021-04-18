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
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    private TokenRepository tokenRepository;
    private MailService mailService;

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
        if (userRepo.findUserByEmail(user.getEmail()).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isUserNameExists(User user) {
        if (userRepo.findByUserName(user.getUsername()).isEmpty()) {
            return false;
        } else {
            return true;
        }
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
}
