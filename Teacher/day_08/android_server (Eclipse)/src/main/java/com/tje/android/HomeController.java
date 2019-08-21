package com.tje.android;

import java.text.DateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object login_submin(
			@RequestParam(value = "id") String id,
			@RequestParam(value = "pw") String pw
			) {
		System.out.printf("id = %s, pw = %s\n", id, pw);
		
		// 클라이언트로 응답을 보내기위한 MAP 객체 생성
		HashMap<String, String> result = new HashMap<>();
		result.put("id", id);
		result.put("pw", pw);
		result.put("r", Boolean.toString(id.equals(pw)));
		
		/*
		{"id" : "admin", "pw" : "admin", "r" : "true" }
		*/
		
		return result;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object login_submin() {
		ArrayList<Member> result = new ArrayList<>();
		
		String [] images = {"b.png", "face.png", "p.png"};
		for( int i = 1 ; i <= 1000 ; i++ ) {
			Member member = new Member();
			member.setName("Name-" + i);
			member.setAge("Age -" + i);
			member.setTel("Tel -" + i);
			member.setImageUri(images[i % images.length]);
			
			result.add(member);
		}
		
		System.out.println(111);
		
		return result;
	}
	
	@GetMapping("/request_get")
	@ResponseBody
	public Object request_get(
			@RequestParam(value="msg", 
			defaultValue="메세지없음") String msg) {
		System.out.println("request_get 실행");
		System.out.printf("msg -> %s\n", msg);
		
		Member member = new Member();
		member.setName("Name-GET");
		member.setAge("Age-GET");
		member.setTel("Tel-GET");
		member.setImageUri("ImageUri-GET");
		
		return member;
	}
	
	@PostMapping("/request_post")
	@ResponseBody
	public Object request_post(
			@RequestParam(value="msg", 
			defaultValue="메세지없음") String msg) {
		
		System.out.println("request_post 실행");
		System.out.printf("msg -> %s\n", msg);
		
		Member member = new Member();
		member.setName("Name-POST");
		member.setAge("Age-POST");
		member.setTel("Tel-POST");
		member.setImageUri("ImageUri-POST");
		
		return member;
	}
	
	@PostMapping("/app_login")
	@ResponseBody
	public Object app_login(
			@RequestParam(value="id") String id,
			@RequestParam(value="pw") String pw,
			HttpSession session) {
		
		System.out.println("app_login 실행");
		System.out.printf("id -> %s, pw -> %s\n", id, pw);
		
		String login_id = (String)
				session.getAttribute("login_id");
		System.out.printf("login_id -> %s\n", login_id);
		
		HashMap<String, String> result = new HashMap<>();
		
		Boolean flag = false;
		
		if( login_id != null ) {
			result.put("result", flag.toString());
			result.put("login_msg",
					"로그인되어 있는 사용자 입니다.(" + login_id + ")");
		} else {			
			result.put("login_msg", "");
			if( id.equals(pw) ) {
				flag = true;
				session.setAttribute("login_id", id);
			}
			
			result.put("result", flag.toString());
		}
		
		return result;
	}
	
	@GetMapping("/app_logout")
	@ResponseBody
	public Object app_logout(HttpSession session) {
		
		System.out.println("app_logout 실행");	
		
		String login_id = (String)
				session.getAttribute("login_id");
		System.out.printf("login_id -> %s\n", login_id);
		
		HashMap<String, String> result = new HashMap<>();
		
		Boolean flag = false;
		
		if( login_id != null ) {
			flag = true;
			result.put("result", flag.toString());
			result.put("logout_msg",
					login_id + " 계정의 로그아웃이 완료되었습니다.");
			
			session.removeAttribute("login_id");
		} else {			
			result.put("result", flag.toString());
			result.put("logout_msg",
					"로그인이 되어있지 않습니다.");
		}
		
		return result;
	}
	
}

















