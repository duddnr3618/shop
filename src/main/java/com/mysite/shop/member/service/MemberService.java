package com.mysite.shop.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysite.shop.member.entity.Member;
import com.mysite.shop.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor 			// 객체생성 할 필요 없어 자동으로 메모리에 로드 -> final  = @autowrited
public class MemberService {

	private final  MemberRepository memberRepository;
	
	public Member saveMember (Member member) {
		
		//중복 처리 메소드
		validateDuplicate(member);
		
		// 중복을 처리하고 나서 DB에 저장
		return memberRepository.save(member);
		
	}

	// 중복 처리하는 메소드
	private void validateDuplicate(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail());
		if(findMember != null) {
			throw new IllegalStateException("이미 등록된 사용자 입니다.");
		}
		
	}
	
	
}
