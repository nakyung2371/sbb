package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.answer.AnswerService;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

@SpringBootTest
public class CreatAnswer_Test {
	
	@Autowired
	private AnswerService answerService;
	private UserService userService;
	
	//AnswerService의 createAnswer(Integer id, String content, SiteUser author)
	//answer 테이블의 값이 잘 들어가는지 테스트
	@Test
	public void createAnswerTest() {
		
		//첫번째 인자: id: question 테이블의 id
		//두번째 인자: content: 답변 내용
		//세번째 인자: author: SiteUser 객체가 
		SiteUser author =
			userService.getUser("user1");
			
		answerService.creatAnswer(1402, "1402 질문에 대한 답변입니다.", author);
	}

}
