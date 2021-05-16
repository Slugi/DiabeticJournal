package pl.diabeticjournal.controllers;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.diabeticjournal.services.MailService;
import pl.diabeticjournal.services.UserService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class FileController {

    private final MailService mailService;
    private final UserService userService;


    @GetMapping("/file")
    public String fileCreator(Principal principal) throws MessagingException, IOException {
        String email = userService.getUserByName(principal.getName()).getEmail();
        mailService.sendMailWithAttachment(email, "Pomiary", "W załączniku znajduje się plik z pomiarami.",
                false, userService.getUserByName(principal.getName()));
        return "fileInfo";
    }


}
