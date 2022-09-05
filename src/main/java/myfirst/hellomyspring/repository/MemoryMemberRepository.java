package myfirst.hellomyspring.repository;

import myfirst.hellomyspring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long,Member> store=new HashMap<>();
    private static long sequence = 0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence); //순번 1추가해서 member에 저장
        store.put(member.getId(), member); // 저장소에 해당 멤버 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        /*
        ofNullable() 메소드는
        명시된 값이 null이 아니면 명시된 값을 가지는 Optional 객체를 반환하며,
        명시된 값이 null이면 비어있는 Optional 객체를 반환합니다.
         */
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member1 -> member1.getName().equals(name))
                .findAny();
        /*
        스트림은 '데이터의 흐름’입니다.
        배열 또는 컬렉션 인스턴스에 함수 여러 개를 조합해서 원하는 결과를 필터링하고 가공된 결과를 얻을 수 있습니다.
        또한 람다를 이용해서 코드의 양을 줄이고 간결하게 표현할 수 있습니다.
        즉, 배열과 컬렉션을 함수형으로 처리할 수 있습니다.

        store인 Map을 store.values()로 리스트로 변환후 람다식을 적용
        마지막에 findAny()로 만족하는 아무거나 바로 반환
        */

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore(){
        store.clear();
    }
}
