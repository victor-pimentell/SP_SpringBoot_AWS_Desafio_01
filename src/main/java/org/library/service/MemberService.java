package org.library.service;

import org.library.model.Member;
import org.library.repository.Repository;

public class MemberService {

    private Repository<Member> repository;

    public MemberService(Repository<Member> repository) {
        this.repository = repository;
    }

    public void registerMember(Member member) {
        repository.insertObj(member);
    }

    public Member getMemberById(Long id) {
        return repository.getObjById(Member.class, id);
    }
}
