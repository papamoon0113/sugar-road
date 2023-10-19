package com.example.demo.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewResultVO {
	private int reviewId;
	private String userId;
	private int storeId;
	private String nickname;
	private String content;
	private LocalDateTime postedDate;
	private int star;
	private String reviewImagePath;
}
