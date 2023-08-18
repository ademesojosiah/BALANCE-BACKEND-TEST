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


    /**
     * Create User service
     *
     * @param user User object to create
     * @return Created user
     */
    public User createUser(User user){
        this.validator(user);

        return userRepository.save(user);
    }




    /**
     * Validate the user before creating.
     *
     * @param user User object to validate
     * @throws IllegalStateException if validation fails
     */
    public void validator(User user){
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if(userOptional.isPresent()) throw new IllegalStateException("username already exist");
        boolean isUserName = user.getUsername() == null;
        boolean isPassword =  user.getPassword() == null;
        if(isUserName) throw new IllegalStateException("username is required");
        else if (isPassword) throw new IllegalStateException("Password is required");
    }

}
