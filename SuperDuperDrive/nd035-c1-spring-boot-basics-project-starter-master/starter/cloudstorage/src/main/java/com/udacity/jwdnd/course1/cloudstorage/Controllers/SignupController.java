package com.udacity.jwdnd.course1.cloudstorage.Controllers;

import com.udacity.jwdnd.course1.cloudstorage.Entity.User;
import com.udacity.jwdnd.course1.cloudstorage.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signupView(){
        return "signup";
    }

    @PostMapping
    public String signupUser (@ModelAttribute User user, Model model){
        String signupError=null;
        if(!userService.usernameAvailability(user.getUsername())){
            signupError="That username is already taken";
        }
        if (signupError==null){
            int rows = userService.createUser(user);
            if(rows<0){
                signupError="Unexpected error, please try again";
            }
        }
        if (signupError == null){
            model.addAttribute("signupSuccess", true);
            return "redirect:/login";
        } else{
            model.addAttribute("signupError", signupError);
        }
        return "signup";
    }
}
