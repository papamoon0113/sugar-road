package com.example.demo.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCommentDTO {
    private int reviewCommentId;
    private String customerId;
    private int reviewId;
    private String content;
    private LocalDateTime postedDate;
}
