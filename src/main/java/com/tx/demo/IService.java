package com.tx.demo;


import java.util.Optional;

/**
 * @author Hamza Ouni
 */
public interface IService {

    Optional<User> saveUserWithoutTransaction(User user);
    Optional<User> saveUserWithinTransaction(User user);
    Long registerUser(User user);

}
