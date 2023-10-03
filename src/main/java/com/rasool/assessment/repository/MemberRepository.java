package com.rasool.assessment.repository;

import com.rasool.assessment.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Rasool
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
}
