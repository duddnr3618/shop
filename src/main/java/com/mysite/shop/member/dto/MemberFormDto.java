package com.mysite.shop.member.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberFormDto {
	
	@NotBlank(message = "이름은 필수 항목입니다.")
	private String name;
	
	@NotEmpty(message = "이메일은 필수 항목 입니다.")
	@Email(message = "이메일 형식으로 입력하세요.")
	private String email;
	
	@NotEmpty(message = "비밀번호는 필수 항목 입니다.")
	@Length(min = 4 , max=12 , message = "비밀번호는 4~12자리 수를 입력하세요.")
	private String password;
	
	@NotEmpty(message = "주소은 필수 항목 입니다.")
	private String address;
	
}
