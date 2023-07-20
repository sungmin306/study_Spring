package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional  // 이걸로 @AfterEach, BeforeEach 역할을 해줄 수 있음 테스트를 할때 트랜젝션을 실행하고 다 끝나면 롤백됨 디비 반영 안됨
class MemberserviceIntegrationTest {

   @Autowired Memberservice memberService;
   @Autowired MemberRepository memberRepository; // test는 맨 끝단에서 생성되기에 그냥 편하게 하면됨 필드 주입
    @Test
    //@Commit // 디비 반영
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");
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

    }

    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}