package com.kidou.hr_oauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kidou.hr_oauth.feighclients.UserFeignClient;
import com.kidou.hr_oauth.model.User;

@Service
public class UserService {

    @Autowired
    private UserFeignClient userFeignClient;

    public User findByEmail(String email) {
        User user = userFeignClient.findByEmail(email).getBody();

        if (user == null) {
            throw new IllegalArgumentException();
        }

        return user;
    }

}
