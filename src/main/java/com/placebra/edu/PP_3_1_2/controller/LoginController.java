package com.placebra.edu.PP_3_1_2.controller;

import com.placebra.edu.PP_3_1_2.dto.CustomUserDetails;
import com.placebra.edu.PP_3_1_2.entity.Role;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LoginController {

    @GetMapping("/")
    public String loginPage(@AuthenticationPrincipal CustomUserDetails userDetails) {

        //если юзер прошел аутентификацию, не пускать на логин форму
        if (userDetails != null) {
            List<String> roles = userDetails.getRoles().stream().map(Role::getName).toList();
            if (roles.contains("ROLE_ADMIN")) {
                return "redirect:/admin";
            } else if (roles.contains("ROLE_USER")) {
                return "redirect:/user";
            }
        }
        return "login";
    }
}
