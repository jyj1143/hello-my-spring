package myfirst.hellomyspring.service;

import myfirst.hellomyspring.domain.Member;
import myfirst.hellomyspring.repository.MemberRepository;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("Spring_test");

        //when
        Long saveId = memberService.join(member);

        //then
        Member result=memberRepository.findById(saveId).get();
        Assertions.assertThat(result.getName()).isEqualTo(member.getName());


    }

    @Test
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("Spring_test1");

        Member member2 = new Member();
        member2.setName("Spring_test1");

        //when
        memberService.join(member1);

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
}