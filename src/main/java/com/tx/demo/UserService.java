package com.tx.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

/**
 * @author Hamza Ouni
 */
@Component
@Slf4j
public class UserService implements IService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionTemplate template;

    @Override
    public Optional<User> saveUserWithoutTransaction(User user) {
        Object r = userRepository.save(user);
        return Optional.of((User)r);
    }

    @Override
    @Transactional
    public Optional<User> saveUserWithinTransaction(User user) {
        log.info("[UserService] saveUserWithinTransaction ");
        Object r = userRepository.save(user);
        return Optional.of((User)r);
    }

    public Long registerUser(User user) {
        Long id = template.execute(status ->  {
            Object r = userRepository.save(user);
            return ((User)r).getId();
        });
        return id;
    }
}
