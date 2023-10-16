package com.example.demo.domain;

import java.nio.channels.MulticastChannel;
import java.time.LocalDateTime;

import lombok.*;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostDTO {
	private int postId;
	private String content;
	private String title;
	private LocalDateTime postedDate;
	private String userId;
	private String postCategoryId;
	private String[] postImage;
	private MultipartFile[] uploadImages;
}
