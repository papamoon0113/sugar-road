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
	private int menuId;
	private int storeId;
	private String menuName;
	private int price;
	private String menuDesc;
	private String dessertId;
	private byte[] image;
}
