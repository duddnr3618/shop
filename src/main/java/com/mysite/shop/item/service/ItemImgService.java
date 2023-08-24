package com.mysite.shop.item.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.mysite.shop.item.entity.ItemImg;
import com.mysite.shop.item.repository.ItemImgRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {
	

	@Value(value="${itemImgLocation}")
	private String itemImgLocation;
	
	private final ItemImgRepository itemimgRepository;
	
	private final FileService fileService;
	
	// DB에서 ItemImg 정보와 
	public void saveItemImg (ItemImg itemImg , MultipartFile itemImgFile) throws IOException {
		String oriImgName = itemImgFile.getOriginalFilename();
		String imgName = "";
		String imgUrl = "";
		
		// 
		if (!StringUtils.isEmpty(oriImgName)) {
			imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
			imgUrl = "/images/item/" + imgName;
			
		}
		
		//DB에 아이템이미지와 이름을 저장
		itemImg.updateItemImg(oriImgName, imgName, imgUrl);
		itemimgRepository.save(itemImg);
		
		
	}

}
