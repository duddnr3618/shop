package com.mysite.shop.item.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.shop.item.dto.ItemDto;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class ItemController {
	

	@GetMapping("Test/ex01")
	public String ex01 (Model model) {
		
		ItemDto itemDto = new ItemDto();
		itemDto.setItemDetail("상품 상세 설명");
		itemDto.setItemName("상품 이름");
		itemDto.setPrice(10000);
		itemDto.setRegTime(LocalDateTime.now());
		
		model.addAttribute("itemDTO" , itemDto);
		
		return "test/ex01";
	}
	
	@GetMapping(value={"Test/ex02","Test/ex03"})
	public void ex02 (Model model) {
		
		List<ItemDto> list = new ArrayList<>();

		for(int i = 1 ; i <=10 ; i++) {
		
		ItemDto itemDto = new ItemDto();
		itemDto.setItemDetail("상품 상세 설명" + i);
		itemDto.setItemName("상품 이름" + i);
		itemDto.setPrice(10000*i);
		itemDto.setRegTime(LocalDateTime.now());
		list.add(itemDto);
		}
		
		model.addAttribute("list" ,list);
		
	}
	
	@GetMapping("Test/ex04")
	public String ex04 (@RequestParam("param1") String p1, String param2 ,Model model) {
		log.info("=========>"  +  p1 + "," + param2);			// 콘솔에 찍힘
		model.addAttribute("param1",p1);
		model.addAttribute("param2",param2);
		return "Test/ex04";
	}
	
	@GetMapping(value = {"/Test/ex05","/Test/ex06"})
	public void ex05 () {
		
	}
	
}
