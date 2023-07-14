package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.repository.MemoryMemberRepositoryTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberserviceTest {

   Memberservice memberService;
   MemoryMemberRepository memberRepository;
    @BeforeEach  //테스트 실행 전에 호출됨 테스트가 서로 영향이 없도록 새로운 객체들을 생성해주고 의존관계도 맺게해준다.
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new Memberservice(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");
        //when
        Long saveId = memberService.join(member);


        //then
        Member findMember= memberService.findOne(saveId).get();
        //Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_에외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // command + option + v 단축키 사기
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//            memberService.join(member1); // 중복되는 것을 try catch로 잡을 수도 있음
//            //fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 사람입니다.");
//        }

        // then
    }

    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}