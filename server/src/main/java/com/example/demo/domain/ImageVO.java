package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class ImageVO {
    private MultipartFile[] uploadImages;
}
