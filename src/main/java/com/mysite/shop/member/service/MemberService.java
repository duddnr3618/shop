package com.mysite.shop.member.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysite.shop.member.entity.Member;
import com.mysite.shop.member.repository.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor 			// 객체생성 할 필요 없어 자동으로 메모리에 로드 -> final  = @autowrited
public class MemberService implements UserDetailsService {

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

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		// 다 같은 문법
//		Optional<Member> member = memberRepository.findByEmail(email)
//		if(!member.isPresent()) {
//		throw new  UsernameNotFoundException("해당 사용자가 없습니다." + email);
//		}
		
//		Member member = memberRepository.findByEmail(email).orElseThrow(new EntityNotFoundException());
//		Member member = memberRepository.findByEmail(email).orElseThrow( EntityNotFoundException::new);
		
		Member member = memberRepository.findByEmail(email);
		if(member==null) {
			throw new  UsernameNotFoundException("해당 사용자가 없습니다." + email);
		}
		
		log.info("-------------------------> loadUserByUsername  " + member);
		
		//생성자를 만들 필요 없이 builder를 통해 객체를 만들수 있다.
		
		
		return User.builder()
				.username(member.getEmail())
				.password(member.getPassword())
				.roles(member.getRole().toString())
				.build();
				
	}
	
	
}
