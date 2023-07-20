package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.Memberservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }  // 스프링 데이터 JPA가 만들어놓은 구현체를 등록



//    private EntityManager em;
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }



//    private DataSource dataSource;
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean //스프링 빈에 등록해라 라는 의미
    public Memberservice memberService(){
        //return new Memberservice(memberRepository());
        return new Memberservice(memberRepository);
    }
//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
////        return new JpaMemberRepository(em);
//
//    }

//    @Bean // 이렇게 해야지 AOP로 등록되어져 있다는 것을 보여줄 수 있음 이런방식이 더 좋으나 예제는 이렇게 안함
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }
}
