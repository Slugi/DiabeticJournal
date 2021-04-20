package pl.diabeticjournal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.diabeticjournal.entity.GlucoseMeasurement;
import pl.diabeticjournal.entity.Insulin;
import pl.diabeticjournal.entity.User;
import pl.diabeticjournal.services.InsulinService;
import pl.diabeticjournal.services.MeasurementService;
import pl.diabeticjournal.services.UserService;
import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class MeasurementController {

    private MeasurementService measurementService;
    private UserService userService;
    private InsulinService insulinService;

    @GetMapping("/addmeasurement")
    public String measurementForm(Model model) {
        model.addAttribute("GlucoseMeasurement", new GlucoseMeasurement());
        return "glucoseMeasurementAdd";
    }

    @PostMapping("/addmeasurement")
    public String userInfoAdd(GlucoseMeasurement measurement, User user, Insulin insulin) {
        measurementService.addMeasurement(measurement, user, insulin);
        return "redirect:allmeasurements";
    }

    @GetMapping("/allmeasurements")
    public String measurements(Model model){
        model.addAttribute("measurements", measurementService.measurements());
        return "AllMeasurements";
    }


    @ModelAttribute("insulins")
    public List<Insulin> insulin() {
        return insulinService.insulinList();
    }

    @ModelAttribute("user")
    public User user(String name, Principal principal) {
        name = principal.getName();
        return userService.getUserByName(name);
    }
}
