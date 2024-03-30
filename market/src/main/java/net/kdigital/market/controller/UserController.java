package net.kdigital.market.controller;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import net.kdigital.market.dto.MemDTO;
import net.kdigital.market.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    /**
     * 회원가입 화면 요청
     * 
     * @return
     */
    @GetMapping("/join")
    public String join() {
        return "user/join";
    }

    /**
     * 회원가입 요청 (DB저장)
     * 
     * @param memDTO
     * @return
     */
    @PostMapping("/join")
    public String join(@ModelAttribute MemDTO memDTO) {

        service.insert(memDTO);

        return "redirect:/";
    }

    /**
     * 로그인 화면 요청
     * 
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

}
