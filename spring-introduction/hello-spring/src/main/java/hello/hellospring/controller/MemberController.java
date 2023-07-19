package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.Memberservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller  //컴포넌트 스캔으로 올라감
public class MemberController {

    private final Memberservice memberService;
    @Autowired  // 연결시켜줄때 사용 (외존관계 주입)
    public MemberController(Memberservice memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMember();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
