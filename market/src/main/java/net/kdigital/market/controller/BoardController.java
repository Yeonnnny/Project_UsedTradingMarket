package net.kdigital.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/board")
public class BoardController {
    
    @GetMapping("/boardList")
    public String boardList() {
        return "board/boardList";
    }
    
    @GetMapping("/boardWrite")
    public String boardWrite() {
        return "board/boardWrite";
    }

    
    

}
