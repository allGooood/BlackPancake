package com.example.blackpancake.user.repository.impl;

import com.example.blackpancake.user.domain.Member;
import com.example.blackpancake.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final EntityManager em;

    @Autowired
    public UserRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member join(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        System.out.println("Email: " + email);
        List<Member> result = em.createQuery("select m from Member m where m.email = :email", Member.class)
                                            .setParameter("email", "gina@gmail.com")
                                            .getResultList();
        System.out.println("EmailResult: " + result.get(0).getEmail());
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
        return result;
    }

    @Override
    public Member login(Member member) {
        return null;
    }

//    @Override
//    public Member login(Member member) {
//        return null;
//    }
}
