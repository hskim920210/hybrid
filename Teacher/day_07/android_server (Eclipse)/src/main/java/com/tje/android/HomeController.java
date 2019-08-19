package com.tje.android;

import java.text.DateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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
	
}

















