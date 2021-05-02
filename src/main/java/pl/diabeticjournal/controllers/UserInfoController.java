package pl.diabeticjournal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.diabeticjournal.entity.User;
import pl.diabeticjournal.entity.UserInfo;
import pl.diabeticjournal.services.UserInfoService;
import pl.diabeticjournal.services.UserService;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@AllArgsConstructor
public class UserInfoController {
    private final UserInfoService userInfoService;
    private final UserService userService;

    @GetMapping("/userinfoadd")
    public String userInfoAddForm(Model model) {
        model.addAttribute("userInfo", new UserInfo());
        return "addUserInfo";
    }

    @PostMapping("/userinfoadd")
    public String userInfoAdd(@Valid UserInfo userInfo, BindingResult result, User user) {
        if (result.hasErrors()) {
            return "addUserInfo";
        }
        userInfoService.addUserInfo(userInfo, user);
        return "Welcome";

    }

    @GetMapping("/showinfo")
    public String showUserInfo(Model model, User user) {
        model.addAttribute("userinfo", userInfoService.showUserInfo(user));
        return "userInfoShow";
    }

    @GetMapping("/edituserinfo")
    public String editUserInfoForm(Model model, User user) {
        model.addAttribute("userInfo", userInfoService.showUserInfo(user));
        return "editUserInfo";
    }

    @PostMapping("/edituserinfo")
    public String editUserInfo(@Valid UserInfo userInfo, BindingResult result, User user) {
        if (result.hasErrors()) {
            return "editUserInfo";
        }
        userInfoService.addUserInfo(userInfo, user);
        return "Welcome";
    }

    @ModelAttribute("user")
    public User user(Principal principal) {
        return this.userService.getUserByName(principal.getName());
    }

    @ModelAttribute("userInfo")
    public UserInfo userinfo(User user) {
        return this.userInfoService.showUserInfo(user);
    }

}
