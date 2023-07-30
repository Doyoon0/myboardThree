package com.myboard3.myboard3.controller;

import com.myboard3.myboard3.entity.User;
import com.myboard3.myboard3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/register")
    public String register() {
        return "account/register";
    }

    @PostMapping("/register")
    public String register(User user) {
        userService.save(user);
        return "redirect:/"; //Home 안에 로직이 있다면 먼저 실행이 된 후에 이동하기 때문에 홈에서 필요한 데이터를 같이 세팅되어서 이동. 더 정확.
    }
}
