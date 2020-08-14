package com.tx.demo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Hamza Ouni
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
