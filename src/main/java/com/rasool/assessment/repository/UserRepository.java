package com.rasool.assessment.repository;


import com.rasool.assessment.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Rasool
 */
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUserId(long userId);
}
