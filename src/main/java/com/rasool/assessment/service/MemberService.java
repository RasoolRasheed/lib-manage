package com.rasool.assessment.service;

import com.rasool.assessment.model.Member;

import java.util.List;

/**
 * @author Rasool
 */
public interface MemberService {

    List<Member> getAllMembers();
    Member getMemberById(Long id);

    Member createMember(Member member);

    Member updateMember(Long id, Member updatedMember);

    void deleteMember(Long id);

}
