package com.tx.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hamza Ouni
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    User saveUserWithoutTransaction(@RequestBody User user) {
        return  userService.saveUserWithoutTransaction(user).get();
    }


    @PostMapping("/saveINT")
    User saveUserWithinransaction(@RequestBody User user) {
        return  userService.saveUserWithinTransaction(user).get();
    }

    @PostMapping("/register")
    Long registerUser(@RequestBody User user) {
        return  userService.registerUser(user);
    }
}
