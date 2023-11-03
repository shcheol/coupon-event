package com.hcs.web.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("인증 체크 인터셉터 실행 {}", request.getRequestURI());
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute(LoginConst.LOGIN_MEMBER) == null) {
			log.info("미인증 사용자 요청");
			response.sendRedirect("/login?redirectURL=" + request.getRequestURI());
			return false;
		}
		return true;

	}
}
