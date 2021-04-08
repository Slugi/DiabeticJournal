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
import pl.diabeticjournal.repository.UserRepository;
import pl.diabeticjournal.services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Controller
@AllArgsConstructor
public class RegistrationController {
  private UserRepository userRepo;
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
  //Zmienić z @ResponseBody na MVC, info o istniejącym mailu
  public String register(User user, HttpServletResponse resp) throws IOException {
    if(!userService.isEmailexists(user)){
    userService.registerUser(user);
      return "Potwierdź rejestrację, klikając w link przesałny w mailu!";
    }else{
      resp.sendRedirect("/register");
      return "Podany email już istnieje, podaj inny.";
    }

  }

  @GetMapping("/token")
  public String token(@RequestParam String value) {

    Token byValue = tokenRepository.findTokenByTokenValue(value).orElseThrow(() -> new RuntimeException("Nie prawidłowa wartość, kliknij w przesłany link. Nie zmieniaj go!"));
    User user = byValue.getUser();
    user.setEnabled(true);
    userRepo.save(user);
    return "redirect:/login";
  }
}
