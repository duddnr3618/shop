package com.mysite.shop.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.shop.member.dto.MemberFormDto;
import com.mysite.shop.member.entity.Member;
import com.mysite.shop.member.service.MemberService;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	// 로그인 창으로 이동
	@GetMapping("/login")
	public String login () {
		return "member/memberLogin";
	}
	
	
	
	@GetMapping("/new")
	public String memberForm (Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		return "member/memberForm";
	}
	
	@PostMapping("/new")
	public String memberForm(@Valid MemberFormDto memberFormDto,
			BindingResult bindingResult , Model model) {
		
		// 로그인 오류 검사
		if (bindingResult.hasErrors()) {
			return "member/memberForm";
		}
		
		try {
			Member member = Member.createMember(memberFormDto, passwordEncoder);
			memberService.saveMember(member);
		} catch(IllegalStateException e) {
			model.addAttribute("errorMessage" ,e.getMessage());
			return "member/memberForm";
		}
		
		// 콘솔에 출력
		//log.info("-----------------> post 성공" + memberFormDto);
		return "redirect:/";
	}
	
	@GetMapping("login/error")
	public String loginError (Model model) {
		model.addAttribute("loginErrorMsg" , "아이디 또는 패스워드가 잘못 되었습니다.");
		return "member/memberLogin"; 
	}

}
