package net.kdigital.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.market.dto.MemDTO;
import net.kdigital.market.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
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
    @PostMapping("/joinProc")
    public String joinProc(@ModelAttribute MemDTO memDTO) {
        log.info("{}", memDTO.toString());

        memDTO.setEnabled(true);
        memDTO.setRolename("ROLE_USER");

        service.insert(memDTO);

        return "redirect:/";
    }

    /**
     * 로그인 화면 요청
     * 
     * @return
     */
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "errMessage", required = false) String errMessage,
            Model model) {
        model.addAttribute("error", error);
        model.addAttribute("errMessage", errMessage);
        return "user/login";
    }

}
