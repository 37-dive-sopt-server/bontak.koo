package org.sopt.domain.member.repository;

import org.sopt.domain.member.entity.Member;

import java.util.*;

public class MemoryMemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();


    public Member save(Member member) {

        store.put(member.getId(), member);
        return member;

    }


    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }


    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }


    public Optional<Member> findByEmail(String email) {
        return store.values().stream()
                .filter(member -> member.getEmail().equals(email))
                .findFirst();
    }


    public boolean deleteById(Long id) {
        if (store.containsKey(id)) {
            store.remove(id);
            return true;
        }
        return false;
    }
}
