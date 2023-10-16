package com.example.demo.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostImageDTO {
    private int postImageId;
    private String postImagePath;
    private int postId;
}
