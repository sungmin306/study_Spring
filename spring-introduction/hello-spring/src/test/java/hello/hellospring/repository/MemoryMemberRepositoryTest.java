package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.util.List;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach // 각 테스트가 종료될때 작동
    public void afterEach() {  // 테스트는 서로 의존관계 없이 설계해야하기떄문에 데이터를 깔끔하게 지워야함
        repository.clearStore();
    }

    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        repository.save(member);

        //then
//        Optional<Member> optionalMember = repository.findById(member.getId()); 이게 더 좋은방법 아래 get() 하는것보다
//        Member result = optionalMember.orElse(null);
        Member result = repository.findById(member.getId()).get();
        //Assertions.assertEquals(member,result);
        assertThat(member).isEqualTo(result);
        //assertj의 assertThat 사용 member와 result를 비교 태스트에서 많이 사용함
    }

    @Test
    public void findByname(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();  // shift f6을 하면 같은 글자 한번에 고치기 가능
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findALL() {
        Member member1 =new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2); // test에서 2개를 만들었으니 2개 가 맞는지 확인



    }
}
