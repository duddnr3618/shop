package com.mysite.shop.item.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.aspectj.apache.bcel.classfile.Field;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileService {
	
	// 경로와 파일명 들고와 특정 위치에 생성 후 그 위치에 이름을 생성
	public String uploadFile(String uploadPath , String oriFileName , byte[] fileDate) 
			throws IOException {
		
		// uuid를 통해 파일 관리
		UUID uuid = UUID.randomUUID();
		//확장자
		String extension = oriFileName.substring(oriFileName.lastIndexOf("."));
		//uuid와 확장자를 사용해서 이미지 이름 생성
		String savedFileName = uuid.toString()+extension;
		// url 
		String fileUploadUrl = uploadPath + "/" + savedFileName; 
		FileOutputStream fos = new FileOutputStream(fileUploadUrl);
		fos.write(fileDate);
		fos.close();
		return savedFileName;
	}
	
	//파일 삭제 기능
	public void deleteFile(String filePath) {
		File deleteFile = new File (filePath);
		
		if(deleteFile.exists()) {
			deleteFile.delete();
			log.info("파일을 삭제 했습니다.");
			
		}else {
			log.info("파일이 존재하지 않습니다.");
		}
		

	}
	

}
