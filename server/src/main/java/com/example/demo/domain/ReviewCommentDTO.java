package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCommentDTO {
    private int reviewCommentId;
    private String memberId;
    private int reviewId;
    private String content;
    private LocalDateTime postedDate;
    private int parentComment;
}
