package org.library.controller;

import org.library.model.Member;
import org.library.service.MemberService;

import java.time.LocalDate;

public class MemberController {

    private MemberService memberService;

    public MemberController() {
        memberService = new MemberService();
    }

    public void registerMember(String name, String address, String phoneNumber, String email, LocalDate associationDate) {
        Member member = new Member(name, address, phoneNumber, email, associationDate);
        memberService.registerMember(member);
    }

    public Member getMemberByEmail(String email) {
        return memberService.getMemberByEmail(email);
    }
}
