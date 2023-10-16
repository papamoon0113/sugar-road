package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {
	private String userId;
	private String userPassword;
	private String userName;
	private String nickname;
	private String userEmail;
	private String userImagePath;
	private MultipartFile image;
}
