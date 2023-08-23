package com.mysite.shop.item.dto;

import java.time.LocalDateTime;

import com.mysite.shop.utils.entity.BaseEntity;

import lombok.Data;

@Data
public class ItemDTO extends BaseEntity {

	private Long id;			// 상품 코드 
	
	private String itemName;		// 상품 이름
	
	private int price;			// 상품 가격
	
	private int stockNumber;	// 재고 수량
	
	private String itemSellStatus;
	
	private String itemDetail;	// 상품 상세 설명 
	
	
	
}
