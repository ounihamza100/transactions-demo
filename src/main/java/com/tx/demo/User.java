package com.tx.demo;

import lombok.Data;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Hamza Ouni
 */
@Entity
@Data
public class User {
    @Id
    private Long id;
    private String username;
}
