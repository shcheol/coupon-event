package com.hcs.web;

import com.hcs.member.MemberDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static com.hcs.web.login.LoginConst.LOGIN_MEMBER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void index() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/home"));

    }

    @Test
    void home() throws Exception {

        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));

    }

    @Test
    void loginHome() throws Exception {

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(LOGIN_MEMBER, "memberId");
        mockMvc.perform(
                        get("/home")
                                .session(session)
                )
                .andExpect(status().isOk())
                .andExpect(model().attribute("member", new MemberDto("memberId")))
                .andExpect(view().name("loginHome"));

    }

    @Test
    void accessInvalidPageNoSession() throws Exception {

        mockMvc.perform(
                        get("/my/main")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?redirectURL=/my/main"));

    }

    @Test
    void accessInvalidPageInvalidSession() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("LOGIN_MEMBERXXX", "memberId");
        mockMvc.perform(
                        get("/my/main")
                                .session(session)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?redirectURL=/my/main"));

    }

    @Test
    void loginMyPage() throws Exception {

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(LOGIN_MEMBER, "memberId");
        mockMvc.perform(
                        get("/my/main")
                                .session(session)
                )
                .andExpect(status().isOk())
                .andExpect(model().attribute("member",new MemberDto("memberId")))
                .andExpect(view().name("my/myMain"));

    }
}