package com.mysite.sbb.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.answer.AnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/question")	//하위 @GetMapping, @PostMapping의 prefix가 적용됨
@RequiredArgsConstructor
@Controller
public class QuestionController {
	
	//@Autowired 타입으로 객체를 주입함. 동일한 타입이 주입될 수 있다, JUnit Test
	
	//생성자를 통한 객체 주입
	//private final QuestionRepository questionRepository;
	
	private final QuestionService questionService;
	
	//http://localhost:8585/question/list
	@GetMapping("/list")
	//@ResponseBody
	public String list(Model model,
			@RequestParam(value = "page", defaultValue="0") int page
			
			) {
		
		//Model: 서버의 데이터를 client view 페이지로 전송
		//메소드 인풋 값으로 선언되면 객체가 자동으로 생성
		
		//client 요청에 대한 비즈니스 로직 처리: question 테이블의 list를 출력하라는 요청
		//List<Question> questionList = questionService.getList();
		
		//페이징 처리된 객체를 받음
		Page<Question> paging = questionService.getList(page);
		
		//paging에 등록 되어있는 중요 메소드 출력
		System.out.println("전체 레코드 수: " + paging.getTotalElements());
		System.out.println("페이지당 출력 레코드 갯수: " + paging.getSize());
		System.out.println("전체 페이지 갯수: " + paging.getTotalPages());
		
		System.out.println("현재 요청 페이지 번호: " + paging.getNumber());
		System.out.println("이전 페이지 존재 여부: " + paging.hasPrevious());
		System.out.println("다음 페이지 존재 여부: " + paging.hasNext());
		
		//model에 담아서 client view 페이지로 전송
		//model: 서버에 있는 데이터를 변수에 담아서 view 페이지까지 전송
		//model.addAttribute("questionList", questionList);
		
		model.addAttribute("paging", paging);
		
		//templates/question_list.html
		//thymeleaf 라이브러리 설치 시 view 페이지가 위치할 곳, .html
		return "question_list";
	}
	
	//상세 글 조회
	//http://localhost:8585/question/detail/{id}
	@GetMapping("/detail/{id}")
	public String detail(Model model,
			@PathVariable("id") Integer id,
			AnswerForm answerForm
			) {
		
		System.out.println(id);
		
		Question question =
				questionService.getQuestion(id);
		
		System.out.println(question.getSubject());
		System.out.println(question.getContent());
		
		//model에 담아서 client로 전송
		model.addAttribute("question", question);
		
		return "question_detail";
	}
	
	//질문 등록: 글 등록 뷰 페이지만 전송
	//http://localhost:8585/question/create
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		
		return "question_form";
	}
	
	//질문 등록 DB에 값을 받아서 저장
	@PostMapping("/create")
	public String questionCreate(
//			@RequestParam("subject") String subject,
//			@RequestParam("content") String content
			@Valid QuestionForm questionForm, BindingResult bindingResult
			) {
		
		if (bindingResult.hasFieldErrors()) {
			return "question_form";
		}
		
		
		/*
		System.out.println("제목: " + subject);
		System.out.println("내용: " + content);
		*/
		questionService.create(questionForm.getSubject(), questionForm.getContent());
		
		return "redirect:/question/list";
	}
}
