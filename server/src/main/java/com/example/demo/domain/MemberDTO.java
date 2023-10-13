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
public class MemberDTO {
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String nickname;
	private String email;
	private String image;
}
