package com.mysite.shop.item.dto;

import org.modelmapper.ModelMapper;

import com.mysite.shop.item.entity.ItemImg;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ItemImgDto {
	

	private Long id;
	
	private String imgName;			// 이미지 파일명
	
	private String oriImgName;		// 실제 이미지 이름
	
	private String imgUrl;
	
	private String repImgYn;	// 대표 이미지 설정
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static ItemImgDto of(ItemImg itemImg) {
		
		return modelMapper.map(itemImg,ItemImgDto.class);
	}
	

}
