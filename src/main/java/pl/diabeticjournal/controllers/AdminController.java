package pl.diabeticjournal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.diabeticjournal.entity.GlucoseMeasurement;
import pl.diabeticjournal.entity.Insulin;
import pl.diabeticjournal.entity.User;
import pl.diabeticjournal.entity.UserInfo;
import pl.diabeticjournal.services.InsulinService;
import pl.diabeticjournal.services.MeasurementService;
import pl.diabeticjournal.services.UserInfoService;
import pl.diabeticjournal.services.UserService;
import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {
    private final InsulinService insulinService;
    private final UserService userService;
    private final MeasurementService measurementService;
    private final UserInfoService userInfoService;

    @GetMapping("/admin")
    public String forAdmin() {
        return "hello-admin";
    }

    @GetMapping("/insulinlistforadmin")
    public String insulinList() {
        return "insulinListForAdmin";
    }

    @GetMapping("/userslist")
    public String usersList(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        return "UsersList";
    }

    @GetMapping("/allmeasurementlist")
    public String listOfAllMeasurements(Model model) {
        model.addAttribute("measurements", measurementService.getAll());
        return "MeasurementsForAdmin";
    }

    @RequestMapping("/deleteinsulin/{id}")
    public String insulinDelete(@PathVariable(value="id") Long id){
      Insulin insulin =  insulinService.getInsulinById(id);
        insulinService.deleteInsulin(insulin);
        return "redirect:/insulinlistforadmin";
    }
    @RequestMapping("/deleteuser/{id}")
    public String userDelete(@PathVariable(value="id") Long id){
        User user = userService.getUserById(id);
        UserInfo userInfo = userInfoService.getUserInfoByUserId(id);
        GlucoseMeasurement measurement = measurementService.getMeasurementByUser(user);
        measurementService.deleteMeasurement(measurement);
        userInfoService.deleteUserInfo(userInfo);
        userService.userDelete(user);
        return "redirect:/userslist";
    }//Nie dokończone

    @ModelAttribute("insulins")
    public List<Insulin> insulin() {
        return insulinService.insulinList();
    }
    @ModelAttribute("user")
    public User user(Principal principal){
        return userService.getUserByName(principal.getName());
    }
}

