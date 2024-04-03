package net.kdigital.market.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.market.dto.BoardDTO;
import net.kdigital.market.dto.MemDTO;
import net.kdigital.market.service.MemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemController {
    private final MemService service;

    /**
     * 회원가입 화면 요청
     * 
     * @return
     */
    @GetMapping("/user/join")
    public String join() {
        return "user/join";
    }

    /**
     * 회원가입 요청 (DB저장)
     * 
     * @param memDTO
     * @return
     */
    @PostMapping("/user/joinProc")
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
    @GetMapping("/user/login")
    public String login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "errMessage", required = false) String errMessage,
            Model model) {
        model.addAttribute("error", error);
        model.addAttribute("errMessage", errMessage);
        return "user/login";
    }


    /**
     * 마이페이지 요청
     * @return
     */
    @GetMapping("/user/mypage")
    public String mypage() {
        return "user/mypage";
    }

    @RequestMapping(value = "/user/myBoardList", method = {RequestMethod.GET})
    public String myBoardList(@RequestParam(name = "memId") String memId, Model model) {
        log.info("여기 마이샵리스트 컨트롤러야");
        List<BoardDTO> list = service.myBoardList(memId);
        model.addAttribute("list", list);
        return "/user/mypage::#result";
    }

    
    
    

}
