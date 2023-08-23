package com.mysite.shop.item.entity;

import java.time.LocalDateTime;

import com.mysite.shop.item.constant.ItemSellStatus;
import com.mysite.shop.utils.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "item")
public class Item extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="item_id")
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String itemName;
	
	@Column(nullable = false)
	private int price;
	
	@Column(nullable = false )
	private int stockNumber;
	
	@Enumerated(EnumType.STRING)
	private ItemSellStatus itemSellStatus;
	
	@Lob			//큰 데이터 (이미지,동영상 등 처리)
	@Column(nullable = false)
	private String itemDetail;
	

	
	
}
