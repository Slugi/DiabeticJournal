package pl.diabeticjournal.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }
    @GetMapping("/admin")
    public String forAdmin(){
        return "hello-admin";
    }

}
