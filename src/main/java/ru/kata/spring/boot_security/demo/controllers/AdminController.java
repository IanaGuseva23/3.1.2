package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    public final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllUsers (Model model) {
        model.addAttribute("allUsers", userService.findAll());
        return "users";
    }
    @GetMapping ("/delete/{id}")
    public String deleteUser (@PathVariable ("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
    @GetMapping ("/update/{id}")
    public String updateUserForm (Model model, @PathVariable ("id") long id) {
        model.addAttribute("update", userService.findOne(id));
        model.addAttribute("allRole", roleService.getRoleList());
        return "update";
    }
    @PatchMapping ("/update")
    public String updateUser (@ModelAttribute ("user") User user, @RequestParam ("roleList") List<String> role) {
        Collection<Role> roleList = userService.getSetOfRoles(role);
        user.setRoles(roleList);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping ("/new")
    public String addNewUser (Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getRoleList());
        return "new";
    }
    @PostMapping ("/new")
    public String addUser (@ModelAttribute ("user") User user, @RequestParam ("role") List<String> role, @PathVariable("id") long id) {
        Collection<Role> roleList = userService.getSetOfRoles(role);
        user.setRoles(roleList);
        userService.addUser(user);
        return "redirect:/admin";
    }

}
