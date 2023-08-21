package com.mysite.shop.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.shop.member.entity.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {
	
	Member findByEmail(String email);
	
}
