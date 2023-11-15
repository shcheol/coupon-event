package com.hcs.web.login;

import com.hcs.member.Member;
import com.hcs.member.MemberDto;
import com.hcs.member.MemberId;
import com.hcs.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final MemberRepository repository;

	private final String LOGIN_FORM = "loginForm";

	@GetMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginForm loginForm) {
		return LOGIN_FORM;
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request) {

		if(!StringUtils.hasText(loginForm.username())){
			bindingResult.addError(new FieldError(LOGIN_FORM, "username","아이디를 입력해주세요."));
			return LOGIN_FORM;
		}

		Optional<Member> member = repository.findById(MemberId.of(loginForm.username()));
		if (member.isEmpty()){
			bindingResult.addError(new FieldError(LOGIN_FORM, "username","없는 아이디 입니다."));
			return LOGIN_FORM;
		}

		HttpSession session = request.getSession();
		session.setAttribute(LoginConst.LOGIN_MEMBER, MemberDto.convert(member.get()));

		return "redirect:/";
	}


	@GetMapping("/logout")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession(false);

		if(session != null){
			session.invalidate();
		}

		return "redirect:/";
	}
}
