package com.hcs.web.login;

import com.hcs.member.Member;
import com.hcs.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.hcs.web.login.LoginConst.LOGIN_FORM;
import static com.hcs.web.login.LoginConst.LOGIN_MEMBER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
@ExtendWith(MockitoExtension.class)
class LoginControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    MemberRepository memberRepository;

    @Test
    void loginHome() throws Exception {
        mockMvc.perform(
                        get("/login")
                ).andExpect(status().isOk())
                .andExpect(view().name("loginForm"));
    }

    @Test
    void login() throws Exception {
        Optional<Member> member = Optional.of(Member.newMember());
        when(memberRepository.findById(any()))
                .thenReturn(member);

        mockMvc.perform(
                        post("/login")
                                .param("username", "test")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andDo(print());

        verify(memberRepository).findById(any());
    }

    @Test
    void loginWithEmptyId() throws Exception {

        mockMvc.perform(
                        post("/login")
                ).andExpect(status().isOk())
                .andExpect(model().attributeHasErrors(LOGIN_FORM))
                .andExpect(view().name("loginForm"));
    }

    @Test
    void loginUnknownMember() throws Exception {
        Optional<Member> member = Optional.empty();
        when(memberRepository.findById(any()))
                .thenReturn(member);

        mockMvc.perform(
                        post("/login")
                                .param("username", "test")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                ).andExpect(status().isOk())
                .andExpect(model().attributeHasErrors(LOGIN_FORM))
                .andExpect(view().name("loginForm"))
                .andDo(print());

        verify(memberRepository).findById(any());
    }

    @Test
    void logout() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(LOGIN_MEMBER, "memberId");

        mockMvc.perform(get("/logout")
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }
}