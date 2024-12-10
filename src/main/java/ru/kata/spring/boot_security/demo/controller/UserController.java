package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.Set;

@Controller
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUserPage(Principal principal, Authentication authentication, Model model, @RequestParam("id") Long id) {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (userService.getIdByUsername(principal.getName()).equals(id) | roles.contains("ROLE_ADMIN")) {
            model.addAttribute("user", userService.getUser(id));
            return "user";
        } else {
            throw new AccessDeniedException("You do not have permission to access this page");
        }

    }
}
