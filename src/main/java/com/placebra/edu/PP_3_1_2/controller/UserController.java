package com.placebra.edu.PP_3_1_2.controller;


import com.placebra.edu.PP_3_1_2.dto.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("")
    public String userPage(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("simpleRoles", userDetails.getSimpleRoles());
        model.addAttribute("activeRole", "user");
        return "user_page";
    }

}
