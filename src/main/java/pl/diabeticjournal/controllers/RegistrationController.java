package pl.diabeticjournal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.diabeticjournal.entity.Token;
import pl.diabeticjournal.entity.User;
import pl.diabeticjournal.repository.TokenRepository;
import pl.diabeticjournal.services.UserService;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class RegistrationController {
  private UserService userService;
  private TokenRepository tokenRepository;
  private PasswordEncoder passwordEncoder;

  @GetMapping("/register")
  public String userRegister(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping("/register")
  @ResponseBody
  public String register(User user) {
    userService.registerUser(user);
    return "Potwierdź rejestrację mailowo.";
  }

  @GetMapping("/token")
  public String token(@RequestParam String value) {
    Token byValue = tokenRepository.findTokenByTokenValue(value).orElseThrow();
    User user = byValue.getUser();
    user.setEnabled(true);
    userService.registerUser(user);
    return "Welcome";
  }
}
