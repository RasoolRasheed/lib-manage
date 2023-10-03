package com.rasool.assessment.controller;

import com.rasool.assessment.model.Member;
import com.rasool.assessment.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Rasool Rasheed
 */
@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getAllMembers(){
        return memberService.getAllMembers();
    }
    @PostMapping
    public Member createMember(@Valid @RequestBody Member member) {
        return memberService.createMember(member);
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable Long id, @Valid @RequestBody Member updatedMember) {
        return memberService.updateMember(id, updatedMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok("Member successfully deleted.");
    }

}