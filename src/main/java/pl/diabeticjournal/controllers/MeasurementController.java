package pl.diabeticjournal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    private final MeasurementService measurementService;
    private final UserService userService;
    private final InsulinService insulinService;

    @GetMapping("/addmeasurement")
    public String measurementForm(Model model) {
        model.addAttribute("GlucoseMeasurement", new GlucoseMeasurement());
        return "glucoseMeasurementAdd";
    }

    @PostMapping(value = "/addmeasurement", produces = "text/html;charset=UTF-8")
    public String addMeasurement(GlucoseMeasurement measurement, User user, Insulin insulin) {
        measurementService.addMeasurement(measurement, user, insulin);
        return "redirect:allmeasurements";
    }

    @GetMapping("/allmeasurements")
    public String measurements(Model model, User user) {
        model.addAttribute("measurements", measurementService.getMeasurementsByUser(user));
        return "AllMeasurements";
    }

    @GetMapping("/editmeasurement/{id}")
    public String MeasurementEditForm(Model model, @PathVariable(value = "id") Long id, @AuthenticationPrincipal User user) {
        model.addAttribute("measurement", measurementService.getMeasurementById(id));
        return "MeasurementEdit";
    }

    @PostMapping("/editmeasurement")
    public String MeasurementEdit(GlucoseMeasurement measurement, @AuthenticationPrincipal User user) {
        measurementService.editMeasurement(measurement, user);
        return "redirect:allmeasurements";
    }

    @ModelAttribute("insulins")
    public List<Insulin> insulins() {
        return insulinService.insulinList();
    }

    @ModelAttribute("user")
    public User user(String name, Principal principal) {
        name = principal.getName();
        return userService.getUserByName(name);
    }

}
