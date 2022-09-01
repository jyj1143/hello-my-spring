package myfirst.hellomyspring.repository;

import myfirst.hellomyspring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findeByName(String name);
    List<Member> findAll();

}
