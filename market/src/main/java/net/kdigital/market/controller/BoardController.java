package net.kdigital.market.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.market.dto.BoardDTO;
import net.kdigital.market.service.BoardService;
import net.kdigital.market.util.Base64.Base64util;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    /**
     * 상품 게시글 목록 화면 요청
     * 
     * @return
     */
    @GetMapping("/boardList")
    public String boardList(Model model) {
        List<BoardDTO> dtoList = service.selectAll();
        model.addAttribute("list", dtoList);
        return "board/boardList";
    }

    /**
     * 상품 게시글 등록 화면 요청
     * 
     * @return
     */
    @GetMapping("/boardWrite")
    public String boardWrite() {
        return "board/boardWrite";
    }

    /**
     * 상품 게시글 등록 요청
     * 
     * @param boardDTO
     * @return
     */
    @PostMapping("/boardWrite")
    public String boardWrite(@ModelAttribute BoardDTO boardDTO) {
        service.insert(boardDTO);

        return "redirect:/board/boardList";
    }

    /**
     * 상품 게시글 조회 요청
     * 
     * @return
     */
    @GetMapping("/boardDetail")
    public String boardDetail(@RequestParam(name = "boardNum") Long boardNum, Model model) {
        BoardDTO dto = service.selectOne(boardNum);

        // Base64 디코딩
        dto.setContents(new String(Base64util.decode(dto.getContents())));

        model.addAttribute("board", dto);
        return "board/boardDetail";
    }

    /**
     * boardNum에 해당하는 게시글 삭제 요청
     * 
     * @param boardNum
     * @return
     */
    @GetMapping("/boardDelete")
    public String boardDelete(@RequestParam(name = "boardNum") Long boardNum) {
        service.delete(boardNum);

        return "redirect:/board/boardList";
    }

    /**
     * 전달 받은 boardNum에 해당하는 게시글의 상품 구매 요청
     * 
     * @param boardNum
     * @return
     */
    @GetMapping("/purchase")
    public String purchase(@RequestParam(name = "boardNum") Long boardNum,
            @RequestParam(name = "buyerId") String buyerId) {
        log.info("==== 구매 요청 in 컨트롤러");
        log.info("boardNum : {}, buyerId : {}", boardNum, buyerId);
        service.purchase(boardNum, buyerId);

        return "redirect:/board/boardList";
    }

}
