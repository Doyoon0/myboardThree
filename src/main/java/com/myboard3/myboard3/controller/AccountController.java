package com.myboard3.myboard3.controller;

import com.myboard3.myboard3.config.WebSecurityConfig;
import com.myboard3.myboard3.entity.User;
import com.myboard3.myboard3.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;
import java.util.logging.Logger;

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
    public String register(@Valid User user, Errors errors, Model model) {

        /* 중복여부도 모델에 추가함 */
        boolean isUsernameDuplicate = userService.checkUsernameDuplicate(user.getUsername());
        boolean isEmailDuplicate = userService.checkEmailDuplicate(user.getEmail());

        model.addAttribute("isUsernameDuplicate", isUsernameDuplicate);
        model.addAttribute("isEmailDuplicate", isEmailDuplicate);

        if(isUsernameDuplicate || isEmailDuplicate || errors.hasErrors()) {
            /* 회원가입 실패시 입력 데이터 값을 유지 */
            model.addAttribute("user", user);

            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
//            에러 메세지를 감싸는 전체 div 에 th:if="${errorMessage}" 라는 조건을 설정할수도 있음
//            model.addAttribute("errorMessage", "");

            return "account/register";
        }

        userService.save(user);
        return "redirect:/";
    }
}
