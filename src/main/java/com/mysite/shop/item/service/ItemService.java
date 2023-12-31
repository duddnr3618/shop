package com.mysite.shop.item.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.shop.item.dto.ItemFormDto;
import com.mysite.shop.item.entity.Item;
import com.mysite.shop.item.entity.ItemImg;
import com.mysite.shop.item.repository.ItemImgRepository;
import com.mysite.shop.item.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {
	
	private final ItemRepository itemRepository;
	
	private final ItemImgRepository itemImgRepository;
	
	private final ItemImgService itemImgService;
	
	public Long saveItem(ItemFormDto itemFormDto , List<MultipartFile> itemImgFileList) 
			throws IOException {
		
		
		Item item = itemFormDto.createItem();
		itemRepository.save(item);
		
		for (int i = 0; i < itemImgFileList.size(); i++) {
			ItemImg itemImg = new ItemImg();
			itemImg.setItem(item);
			
			if(i==0) {
				itemImg.setRepImgYn("Y");
			}else {
				itemImg.setRepImgYn("N");
			}
			
			itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
			
		}
		
		return item.getId();
	}
	
	

}
