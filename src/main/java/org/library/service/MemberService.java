package org.library.service;

import org.library.exception.MemberNotFoundException;
import org.library.model.Member;
import org.library.repository.Repository;

import javax.persistence.NoResultException;

public class MemberService {

    private Repository<Member> repository;

    public MemberService() {
        repository = new Repository<>();
    }

    public void registerMember(Member member) {
        repository.insertObj(member);
    }

    public Member getMemberById(Long id) {
        return repository.getObjById(Member.class, id);
    }

    public Member getMemberByEmail(String email) {

        try {
            return repository.getObjByEmail(Member.class, email);
        } catch (NoResultException e) {
            throw new MemberNotFoundException("There is no member with this email");
        }
    }
}
