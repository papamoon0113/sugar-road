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
public class MenuDTO {
	// menuId, menuName, price, menuDesc, storeId, menuImagePath 필드 남기기
	private int menuId;
	private String menuName;
	private int price;
	private String menuDesc;
	private int storeId;
	private String menuImagePath;
}
