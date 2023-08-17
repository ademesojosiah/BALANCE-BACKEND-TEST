package com.balancee.test.service;

import com.balancee.test.models.User;
import com.balancee.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        this.validator(user);

        return userRepository.save(user);
    }
    public void validator(User user){
        Optional<User> userOptional = userRepository.findByUserName(user.getUserName());
        if(userOptional.isPresent()) throw new IllegalStateException("username already exist");
        boolean isUserName = user.getUserName() == null;
        boolean isPassword =  user.getPassword() == null;
        if(isUserName) throw new IllegalStateException("username is required");
        else if (isPassword) throw new IllegalStateException("Password is required");
    }

}
