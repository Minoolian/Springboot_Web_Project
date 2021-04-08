package org.zerock.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.zerock.controller.domain.board.Member;
import org.zerock.controller.service.MemberService;

@Controller
@Slf4j
public class SampleController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/signup")
    public void signUp(Member member, @ModelAttribute("authid") Long authid) {
        memberService.save(member, authid);

    }

    @GetMapping("/signup")
    public String signUpView() {
        return "signup";
    }

    @GetMapping("/login")
    public String login(String error, String logout, Model model) {
        if (error != null) {
            model.addAttribute("error", "Login error");
        }

        if (logout != null) {
            model.addAttribute("logout", "Logout!");
        }

        return "login";
    }

    @GetMapping("/accessError")
    public String accessDenied(Authentication auth, Model model) {
        log.info("access denied: " + auth);
        model.addAttribute("msg", "access denied");

        return "accesserror";
    }
}

