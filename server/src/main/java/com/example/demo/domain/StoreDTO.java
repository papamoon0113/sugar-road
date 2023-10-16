package com.example.demo.domain;

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
public class StoreDTO {
	// storeId(String -> int), storeId,storeName,address,phoneNumber,
	// storeDesc,latitude,longitude, storeImagePath, userId 남기기
	private int storeId;
	private String storeName;
	private String address;
	private String phoneNumber;
	private String storeDesc;
	private float latitude;
	private float longitude;
	private String storeImagePath;
	private String userId;
}
