package org.library.service;

import org.library.exception.MemberAlreadyRegisteredException;
import org.library.exception.MemberNotFoundException;
import org.library.model.Member;
import org.library.repository.Repository;

import java.util.Optional;

public class MemberService {

    private Repository<Member> repository;

    public MemberService() {
        repository = new Repository<>();
    }

    public void registerMember(Member member) {
        Optional<Member> memberDataBase = getMemberByEmail(member.getEmail());

        if (memberDataBase.isPresent()) {
            if (memberDataBase.get().getEmail().equalsIgnoreCase(member.getEmail())) {
                throw new MemberAlreadyRegisteredException("This member is already registered.");
            }
        }
        repository.insertObj(member);
    }

    public Member getMemberById(Long id) {
        return repository.getObjById(Member.class, id);
    }

    public Optional<Member> getMemberByEmail(String email) {
        return repository.getObjByEmail(Member.class, email);
    }

    public Member logMemberByEmail(String email) {
        Optional<Member> optionalMember = repository.getObjByEmail(Member.class, email);
        if (optionalMember.isPresent()) {
            return optionalMember.get();
        } else {
            throw new MemberNotFoundException("There is no member with this email");
        }
    }
}
