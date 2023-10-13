package com.example.demo.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentDTO {
	private int postCommentId;
	private String customerId;
	private int postId;
	private String content;
	private LocalDateTime postedDate;
	private int parentComment;
}
