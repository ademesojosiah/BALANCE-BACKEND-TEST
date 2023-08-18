package com.balancee.test.controller;


import com.balancee.test.models.User;
import com.balancee.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")

public class UserController {

    @Autowired
    private UserService userService;


    /**
     * Create a new user (signup).
     *
     * @param user User object to create
     * @return Created user
     */

    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

}
