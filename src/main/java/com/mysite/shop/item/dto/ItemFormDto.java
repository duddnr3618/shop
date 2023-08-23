package com.mysite.shop.item.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.mysite.shop.item.constant.ItemSellStatus;
import com.mysite.shop.item.entity.Item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemFormDto {
	
	private Long id;

	@NotBlank(message = "상품명은 필수 항목입니다.")
	private String itemName;

	@NotNull(message = "상품가격은 필수 항목입니다.")
	private int price;
	
	@NotNull(message = "재고는 필수 항목입니다.")
	private int stockNumber;
	
	private ItemSellStatus itemSellStatus;
	
	@NotBlank(message = "상품설명은 필수 항목입니다.")
	private String itemDetail;
	
	private List<ItemImgDto> itemImgDtoList = new ArrayList<>();
	
	private List<Long> itemImgIds = new ArrayList<>();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Item createItem() {
		return modelMapper.map(this, Item.class);
	}
	
	public static ItemFormDto of(Item item) {
		return modelMapper.map(item, ItemFormDto.class);
		
		
	}
	
	
}
