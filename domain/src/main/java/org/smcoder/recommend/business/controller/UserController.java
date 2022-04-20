package org.smcoder.recommend.business.controller;

import org.smcoder.recommend.business.model.dto.LoginUserRequest;
import org.smcoder.recommend.business.model.dto.RegisterUserRequest;
import org.smcoder.recommend.business.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/rest/users")
@Controller
public class UserController {

    @Autowired
    private UserHandler userService;

    @RequestMapping(value = "/login", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Model login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        User user = userService.loginUser(new LoginUserRequest(username, password));
        model.addAttribute("success", user != null);
        model.addAttribute("user", user);
        return model;
    }

    @RequestMapping(value = "/register", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Model addUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        if (userService.checkUserExist(username)) {
            model.addAttribute("success", false);
            model.addAttribute("message", " 用户名已经被注册！");
            return model;
        }
        model.addAttribute("success", userService.registerUser(new RegisterUserRequest(username, password)));
        return model;
    }
}