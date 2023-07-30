package com.myboard3.myboard3.service;

import com.myboard3.myboard3.entity.Role;
import com.myboard3.myboard3.entity.User;
import com.myboard3.myboard3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnable(true);
        Role role = new Role();
        role.setId(1); // role 테이블에 ROLE_USER 권한의 id 가 1 이어서 하드코딩 해줌. 간단한 방법.
        user.getRoles().add(role); // NullPointException 때문에 service 에서 add
        return userRepository.save(user);
    }

}
