package org.library.service;

import org.library.model.Member;
import org.library.repository.Repository;

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
}
