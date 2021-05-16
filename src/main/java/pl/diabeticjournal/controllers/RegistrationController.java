package pl.diabeticjournal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.diabeticjournal.entity.Token;
import pl.diabeticjournal.entity.User;
import pl.diabeticjournal.repository.TokenRepository;
import pl.diabeticjournal.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final TokenRepository tokenRepository;

    @GetMapping("/register")
    public String userRegister(Model model) {
        model.addAttribute("user", new User());
        return "registerForm";
    }

    @PostMapping("/register")
    public String register(User user, HttpServletResponse resp, HttpServletRequest req, RedirectAttributes redirAttrs) throws IOException {
        String schema = req.getScheme();
        String serverName = req.getServerName();
        int serverPort = req.getServerPort();
        String shemaPort;

        if (("http".equals(schema) && serverPort == 80) || ("https".equals(schema) && serverPort == 443)) {
            shemaPort = "";
        } else {
            shemaPort = ":" + serverPort;
        }

        if (!userService.isEmailexists(user) || !userService.isUserNameExists(user)) {
            userService.registerUser(user, schema + "://" + serverName + shemaPort);
            return "SuccesRegister";
        } else {
            redirAttrs.addFlashAttribute("error", "Username or E-mail already in use!");
            return "redirect:/register";
        }
    }

    @GetMapping("/token")
    public String token(@RequestParam String value) {

        Token byValue =
                tokenRepository
                        .findTokenByTokenValue(value)
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Nie prawidłowa wartość, kliknij w przesłany link. Nie zmieniaj go!"));
        User user = byValue.getUser();
        userService.activate(user);

        return "redirect:/login";
    }
}
