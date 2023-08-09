package com.myboard3.myboard3.service;

import com.myboard3.myboard3.entity.User;
import com.myboard3.myboard3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("USER");
        return userRepository.save(user);
    }

    /* 회원가입 시, 유효성 체크 */
    @Transactional
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        /* 유효성 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    @Transactional
    public boolean checkUsernameDuplicate(String username) {
        return userRepository.existsByUsername(username);
    }

    @Transactional
    public boolean checkEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }
}
