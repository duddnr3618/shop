package com.mysite.shop.cart.entity;

import com.mysite.shop.member.entity.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor			// 기본생성자 생성
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private Long id;
	
	@OneToOne(fetch=FetchType.LAZY)			
	@JoinColumn(name = "member_id")		// Member에 있는 member_id를 FK 하여 상속
	private Member member;
	
	
	
	
}
