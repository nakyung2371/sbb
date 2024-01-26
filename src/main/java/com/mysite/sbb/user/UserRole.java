package com.mysite.sbb.user;

import lombok.Getter;

@Getter	//값을 가져오는 거기 때문에 getter만 넣음
public enum UserRole {
	//선언된 이외의 값은 넣지 못하도록 설정, 권한(USER, ADMIN), 상품 카테고리, 요일
	//인증 후 사용자에게 권한을 부여할 때 사용함
		UESR("ROLE_USER")
		, ADMIN("ROLE_ADMIN");
	
	UserRole(String value) {
		this.value = value;
	}
	
	private String value;
}
