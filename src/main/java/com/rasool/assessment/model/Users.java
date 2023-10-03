package com.rasool.assessment.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Component
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long userId;
    private String username;
    private String password;
    private String email;
    private String contactNo;
    private String tempToken;
    private String status;

}
