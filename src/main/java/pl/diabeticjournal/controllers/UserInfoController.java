package pl.diabeticjournal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.diabeticjournal.entity.User;
import pl.diabeticjournal.entity.UserInfo;
import pl.diabeticjournal.repository.UserInfoRepository;
import pl.diabeticjournal.services.UserInfoService;
import pl.diabeticjournal.services.UserService;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@AllArgsConstructor
public class UserInfoController {
  private UserInfoService userInfoService;
  private UserService userService;

  @GetMapping("/userinfoadd")
  public String userInfoAddForm(Model model) {
    model.addAttribute("userInfo", new UserInfo());
    return "addUserInfo";
  }

  @PostMapping("/userinfoadd")
  public String userInfoAdd(@Valid UserInfo userInfo,BindingResult result, User user) {
    if (result.hasErrors()) {
      return "addUserInfo";
    }
    userInfoService.addUserInfo(userInfo, user);
    return "Welcome";
  }

  @ModelAttribute("user")
  public User user(String name, Principal principal) {
    name = principal.getName();
    return this.userService.getUserByName(name);
  }
}
