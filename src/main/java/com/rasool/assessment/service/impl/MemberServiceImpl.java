package com.rasool.assessment.service.impl;

import com.rasool.assessment.model.Member;
import com.rasool.assessment.repository.MemberRepository;
import com.rasool.assessment.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rasool
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Member updateMember(Long id, Member updatedMember) {
        Member member = memberRepository.findById(id).orElse(null);

        if (member != null) {
            member.setName(updatedMember.getName());
            member.setEmail(updatedMember.getEmail());
            member.setBorrowedBooks(updatedMember.getBorrowedBooks());
            return memberRepository.save(member);
        }
        return null;
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
