package com.example.demo.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private int reviewId;
    private String customerId;
    private int storeId;
    private String content;
    private LocalDateTime postedDate;
    private int star;
}
