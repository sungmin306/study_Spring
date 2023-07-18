package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
//@Repository
public class MemoryMemberRepository implements  MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 동시성 고려 x 단순하게
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id값을 세팅
        store.put(member.getId(), member); //getId와 member를 store에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // store.get(id)가 null인지 알 수 없기에 ofNullable을 사용 null 일경우 NPE를 던지지 않고 Optional.empty()와 동일하게 비어있는 Optional 객체를 얻어옴
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()  //람다표현식 익명 메세지
                .filter(member -> member.getName().equals(name))
                .findAny();  //일치하는 요소 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {  //test할때 초기화될때 이용
        store.clear();
    }
}
