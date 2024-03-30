package net.kdigital.market.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import net.kdigital.market.dto.CommentDTO;
import net.kdigital.market.service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService service;

    /**
     * 전달받은 boardNum의 모든 코멘트 반환하는 함수
     * 
     * @return
     */
    @GetMapping("/commentAll")
    @ResponseBody
    public List<CommentDTO> commentAll(@RequestParam(name = "boardNum") Long boardNum) {

        List<CommentDTO> result = service.selectAll(boardNum);

        return result;
    }

    /**
     * 코멘트 등록 요청
     * 
     * @param commentDTO
     * @return
     */
    @GetMapping("/commentWrite")
    public CommentDTO commentWrite(@RequestParam(name = "boardNum") Long boardNum,
            @RequestParam(name = "commentText") String commentText,
            @RequestParam(name = "memId") String memId) {
        CommentDTO commentDTO = new CommentDTO(boardNum, memId, commentText);

        CommentDTO dto = service.insert(commentDTO);

        return dto;
    }

}
