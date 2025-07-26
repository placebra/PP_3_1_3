package com.placebra.edu.PP_3_1_2.controller;

import com.placebra.edu.PP_3_1_2.dto.CustomUserDetails;
import com.placebra.edu.PP_3_1_2.entity.Role;
import com.placebra.edu.PP_3_1_2.entity.User;
import com.placebra.edu.PP_3_1_2.service.RoleService;
import com.placebra.edu.PP_3_1_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setuserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }



    @GetMapping("")
    public String adminPage(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("simpleRoles", userDetails.getSimpleRoles());
        model.addAttribute("activeRole", "admin");
        model.addAttribute("allUsers", userService.findAllUsers());
        return "admin_page";
    }

    @PostMapping("/remove")
    public String removeUser(@RequestParam int user_id_modal) {
        userService.removeUserById(user_id_modal);
        return "redirect:/admin";
    }

    @PostMapping("/newUser")
    public String addNewUser(@RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam int age,
                             @RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String role) {

        if (userService.findUserByEmail(email) != null) {
            return "redirect:/admin?error#new-user";
        }

        userService.saveUser(firstName, lastName, age, email, password, role);
        return "redirect:/admin?success";
    }

    @PostMapping("/updateUserInfo")
    public String updateUserInfo(@RequestParam(name = "user_id_modal") int id,
                                 @RequestParam(name = "first_name_modal") String firstName,
                                 @RequestParam(name = "last_name_modal") String lastName,
                                 @RequestParam(name = "age_modal") int age,
                                 @RequestParam(name = "email_modal") String email,
                                 @RequestParam(name = "roles_modal") String role,
                                 @RequestParam(name = "password_modal", required = false) String password
                                 ) {

        userService.updateUserInfo(id, firstName, lastName, age, email, role, password);

        return "redirect:/admin";
    }
}
