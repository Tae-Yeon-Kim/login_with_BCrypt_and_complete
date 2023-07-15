package com.codingrecipe.member.controller;
// 회원가입 로그인 요청처리 관련해서 작성!!

import com.codingrecipe.member.dto.MemberDTO;
import com.codingrecipe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    // 생성자 주입
    private final MemberService memberService;


    // 회원가입 페이지 출력 요청!
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.save~!~!~!~!");
        System.out.println("제발.. : "+memberDTO);
        memberService.save(memberDTO);
        return "index";
    }

    @GetMapping("/member/login")
    public String login() {
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO) {
        MemberDTO loginResult = memberService.login(memberDTO);

        if (loginResult != null) {
            //login 성공!!

            return "temp2";
        } else {
            // 실패!
            return "login";

        }
    }

    //원본
    /*@GetMapping("/member/logindashboard")
    public String login22() {
        return "temp3";
    }*/

    @GetMapping("/member/logindashboard")
    public String login22(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("authorities", user.getAuthorities());
        model.addAttribute("loginId", user.getUsername());
        model.addAttribute("loginpassword", user.getPassword());

        System.out.println("authorities : "+user.getAuthorities());
        System.out.println("loginId : "+user.getUsername());
        System.out.println("loginpassword : "+user.getPassword());
        return "temp3";
    }


}

