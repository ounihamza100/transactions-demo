package com.tx.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Hamza Ouni
 */
@Component
public class UserService2 {

    @Autowired
    private UserService userService;
    @Transactional
    public Long test(User user) {
       return userService.mandatoryTransactions(user);
    }
}
