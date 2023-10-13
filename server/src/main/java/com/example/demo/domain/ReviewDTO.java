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
public class ReviewDTO {
    private int reviewId;
    private String memberId;
    private int storeId;
    private String content;
    private LocalDateTime postedDate;
    private int star;
}
