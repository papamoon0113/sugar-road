package com.example.demo.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecommendationDTO {
    private int recommendationId;
    private String referenceType;
    private int referenceId;
    private String userId;

    private LocalDateTime postedDate;
}
