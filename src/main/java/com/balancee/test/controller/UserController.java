package com.balancee.test.controller;


import com.balancee.test.models.User;
import com.balancee.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")

public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;


    /**
     * Create a new user (signup).
     *
     * @param username User object to create
     * @param password object to create
     * @return Created user
     */

    @GetMapping
    public ResponseEntity<User> createUser(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password){
        User user = new User(username,this.encoder.encode(password));
        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

}
