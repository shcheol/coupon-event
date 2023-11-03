package com.hcs.web;

import com.hcs.member.MemberDto;
import com.hcs.web.login.LoginConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {

	@GetMapping
	public String index(){
		return "redirect:/home";
	}

	@GetMapping("/home")
	public String home(@SessionAttribute(name = LoginConst.LOGIN_MEMBER, required = false) MemberDto loginMember, Model model){

		if (loginMember == null){
			return "home";
		}

		model.addAttribute("member", loginMember);
		return "loginHome";
	}

	@GetMapping("/my/main")
	public String myPage(@SessionAttribute(name = LoginConst.LOGIN_MEMBER, required = false) MemberDto loginMember, Model model){

		if (loginMember == null){
			return "home";
		}

		model.addAttribute("member", loginMember);
		return "my/myMain";
	}

}
