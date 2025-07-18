package com.placebra.edu.PP_3_1_2.controller;

import com.placebra.edu.PP_3_1_2.entity.Role;
import com.placebra.edu.PP_3_1_2.entity.User;
import com.placebra.edu.PP_3_1_2.service.RoleService;
import com.placebra.edu.PP_3_1_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("")
    public String adminPage(Model model){

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return "main";
    }

    @GetMapping("/add_user")
    public String newUser() {
        return "new_user";
    }

    @PostMapping("/add_user")
    public String saveUser(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam(name = "phone_number") String phoneNumber,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String role,
                           Model model) {

        if (userService.findUserByUsername(username) != null) {
            model.addAttribute("error", "Пользователь с таким Username уже создан");
            return "new_user";
        }

        User user = new User(name, email, phoneNumber, username, passwordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();

        if (role.equals("ROLE_ADMIN")) {
            roles.add(roleService.getAdminRole());
            roles.add(roleService.getUserRole());
        } else if (role.equals("ROLE_USER")) {
            roles.add(roleService.getUserRole());
        }

        user.setRoles(roles);

        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/remove")
    public String removeUser(@RequestParam int id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/update")
    public String egitUserPage(@RequestParam int id, Model model) {

        User user = userService.getUserById(id);
        model.addAttribute("user", user);


        return "update_user";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam int id,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String email,
                             @RequestParam(required = false, name = "phone_number") String phoneNumber) {

        if (name != null) {
            userService.updateUserName(id, name);
        } else if (email != null) {
            userService.updateUserEmail(id, email);
        } else if (phoneNumber != null) {
            userService.updateUserPhoneNumber(id, phoneNumber);
        }

        return "redirect:/admin";
    }
}
