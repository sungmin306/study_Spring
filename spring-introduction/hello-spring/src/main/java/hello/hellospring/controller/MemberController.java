package hello.hellospring.controller;

import hello.hellospring.service.Memberservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller  //컴포넌트 스캔으로 올라감
public class MemberController {

    private final Memberservice memberService;
    @Autowired  // 연결시켜줄때 사용 (외존관계 주입)
    public MemberController(Memberservice memberService) {
        this.memberService = memberService;
    }
}
