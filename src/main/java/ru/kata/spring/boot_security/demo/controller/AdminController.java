package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.entity.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Controller
public class AdminController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleServiceImpl;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleServiceImpl) {
        this.userService = userService;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping(value = "/admin/users")
    public String getAllUsers(ModelMap model) {
        model.addAttribute("users", userService.getListOfAllUsers());
        return "allUsers";
    }

    @GetMapping(value = "/admin/addUser")
    public String getFormForAddUser(@ModelAttribute("user") User user) {
        return "addUser";
    }

    @PostMapping(value = "/admin/users")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam("role") String role) {


        user.setRoles(roleServiceImpl.getSetOfRoles(role));
        userService.addUser(user);

        return "redirect:/admin/users";
    }

    @GetMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") Long id) {

        userService.deleteUser(id);

        return "redirect:/admin/users";
    }

    @GetMapping("/admin/update")
    public String getFormForUpdateUser(Model model, @RequestParam("id") Long id) {

        model.addAttribute("user", userService.getUser(id));

        return "updateUser";
    }

    @PostMapping("/admin/update")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("role") String role) {

        user.setRoles(roleServiceImpl.getSetOfRoles(role));
        userService.updateUser(user);

        return "redirect:/admin/users";
    }
}
