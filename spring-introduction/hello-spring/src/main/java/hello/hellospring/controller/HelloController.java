package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")  // url 매핑인거같음
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // hello라는 파일을 찾아서 렌더링해라 라는 의미(templates 아래에 있는 hello를 찾음)
    }

    @GetMapping("hello-mvc")
    public String helloMVc(@RequestParam(name= "name", required = false) String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; //"hello spring"
    }


    // 진짜 real
    // JSON 값을 보냄
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;

    }

    static class Hello {  //static class를 이욯하면 class 내 클래스 사용 가능
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
