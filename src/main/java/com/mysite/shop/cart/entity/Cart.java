package com.mysite.shop.cart.entity;

import com.mysite.shop.member.entity.Member;
import com.mysite.shop.utils.entity.BaseEntity;

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
public class Cart extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private Long id;
	
	@OneToOne(fetch=FetchType.LAZY)			// Lazy 타입으로 하면 성능 향상에 도움됨.
	@JoinColumn(name = "member_id")		// Member에 있는 member_id를 FK 하여 상속
	private Member member;
	
	
	
	
}
