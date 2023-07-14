package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;
public interface MemberRepository {
    Member save(Member member);  // 회원을 저장하는 메소드
    Optional<Member> findById(Long id); //id를 찾는 메소드
    Optional<Member> findByName(String name); //이름을 찾는 메소드
    List<Member> findAll(); //저장된 회원의 리스트를 모두 가져오는 메소드

}

/*
Optional 이란 개발할때 가장 많이 발생하는 NPE(NullPointerException)을 방지해줄 수 있다 Optional 은 클래스 이기에 각종 메소드 역시 제공해준다.
 */
