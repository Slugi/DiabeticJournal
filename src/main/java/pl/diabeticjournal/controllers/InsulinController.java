package pl.diabeticjournal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.diabeticjournal.entity.Insulin;
import pl.diabeticjournal.services.InsulinService;



@Controller
@AllArgsConstructor
public class InsulinController {

    private final InsulinService insulinService;


    @GetMapping("/insulin")
    public String insulinForm(Model model) {
        model.addAttribute("insulin", new Insulin());
        return "addInsulin";
    }

    @PostMapping("/insulin")
    public String addInsulin(Insulin insulin) {
        insulinService.addInsulin(insulin);
        return "redirect:/insulinlist";
    }

    @GetMapping("/insulinlist")
    public String insulinList(Model model) {
        model.addAttribute("insulins", insulinService.insulinList());
        return "insulinList";
    }
}
