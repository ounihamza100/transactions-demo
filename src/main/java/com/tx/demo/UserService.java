package com.tx.demo;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
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
//
//    @Autowired
//    private SessionFactory sessionFactory;

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

    /**
     * In this case, Spring will expect a transaction to be open, whenever you call mandatoryTransactions().
     * It does not open one itself, instead, if you call that method without a pre-existing transaction, Spring will throw an exception.
     * @Transactional(isolation = Isolation.REPEATABLE_READ) leads to this ==> connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
     * @param user
     * @return
     */

    @Transactional
    public void test() {

    }
    @Transactional(propagation = Propagation.MANDATORY)
    public Long mandatoryTransactions(User user) {
        Long id = template.execute(status ->  {
            Object r = userRepository.save(user);
            return ((User)r).getId();
        });
        return id;
    }


//
//    public void saveUserOldHibernate(User user) {
//        Session session = sessionFactory.openSession(); // (2)
//
//        // lets open up a transaction. remember setAutocommit(false)!
//        session.beginTransaction();
//
//
//        // save == insert our objects
//        session.save(user);
//
//        // and commit it
//        session.getTransaction().commit();
//
//        // close the session == our jdbc connection
//        session.close();
//    }
}
