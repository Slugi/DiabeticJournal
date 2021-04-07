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

  public void registerUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setUserName(user.getUsername());
    userRepo.save(user);
    sendToken(user);
  }

  private void sendToken(User user) {
    String tokenValue = UUID.randomUUID().toString();
    Token token = new Token();
    token.setTokenValue(tokenValue);
    token.setUser(user);
    tokenRepository.save(token);
    String url = "http://localhost:8080/token?value=" + tokenValue;
    try {
      mailService.sendMail(user.getUsername(), "Potwierdzenie rejestracji!", url, false);
    } catch (MessagingException e) {
      e.printStackTrace();
    }

  }


}
