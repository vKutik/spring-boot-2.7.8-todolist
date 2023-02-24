package com.example.test.controller;

import com.example.test.model.User;
import com.example.test.service.role.RoleService;
import com.example.test.service.user.UserService;
import java.util.Set;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
public class InjectController {
    private UserService userService;
    private RoleService roleService;

    private PasswordEncoder passwordEncoder;

    public InjectController(UserService userService, RoleService roleService,
        PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }
    @RequestMapping("/roles")
    public String injectRole() {
        roleService.createByName("USER");
        roleService.createByName("ADMIN");
        return "roles created";
    }
    @RequestMapping
    public String inject() {
        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("123123"));
        user.setRoles(Set.of(roleService.getByName("USER")));
        userService.create(user);
        return "inject";
    }

    @GetMapping("/user")
    public String getUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
