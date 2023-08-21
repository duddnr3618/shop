package com.mysite.shop.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.shop.member.dto.MemberFormDto;
import com.mysite.shop.member.entity.Member;
import com.mysite.shop.member.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/new")
	public String memberForm (Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		return "member/memberForm";
	}
	
	@PostMapping("/new")
	public String memberForm(MemberFormDto memberFormDto) {
		log.info("-----------------> post 성공" + memberFormDto);
		Member member = Member.createMember(memberFormDto, passwordEncoder);
		memberService.saveMember(member);
		return "redirect:/";
	}

}
