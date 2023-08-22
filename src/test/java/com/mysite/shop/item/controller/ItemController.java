package com.mysite.shop.item.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.shop.item.dto.ItemDTO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class ItemController {
	

	@GetMapping("Test/ex01")
	public String ex01 (Model model) {
		
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemDetail("상품 상세 설명");
		itemDTO.setItemName("상품 이름");
		itemDTO.setPrice(10000);
		itemDTO.setRegTime(LocalDateTime.now());
		
		model.addAttribute("itemDTO" , itemDTO);
		
		return "test/ex01";
	}
	
	@GetMapping(value={"Test/ex02","Test/ex03"})
	public void ex02 (Model model) {
		
		List<ItemDTO> list = new ArrayList<>();

		for(int i = 1 ; i <=10 ; i++) {
		
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setItemDetail("상품 상세 설명" + i);
		itemDTO.setItemName("상품 이름" + i);
		itemDTO.setPrice(10000*i);
		itemDTO.setRegTime(LocalDateTime.now());
		list.add(itemDTO);
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
