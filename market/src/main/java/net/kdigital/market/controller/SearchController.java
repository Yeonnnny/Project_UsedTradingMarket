package net.kdigital.market.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import net.kdigital.market.dto.BoardDTO;
import net.kdigital.market.service.BoardService;
import net.kdigital.market.service.SearchService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final SearchService service;
    private final BoardService boardService;

    /**
     * 전달받은 카테고리에 해당하는 게시글 목록 요청
     * 
     * @param category
     * @return
     */
    @GetMapping("/searchList")
    public String searchList(@RequestParam(name = "category", defaultValue = "total") String category,
                            @RequestParam(name = "searchWord", defaultValue = "") String searchWord, 
                            Model model) {
        List<BoardDTO> dtoList = new ArrayList<>();

        if (category.equals("total")) {
            dtoList = boardService.selectAll();
        }else{
            dtoList = service.selectAllBySearching(category,searchWord);
        }
        
        model.addAttribute("list", dtoList);
        model.addAttribute("category", category);
        model.addAttribute("searchWord", searchWord);
        
        return "search/searchList";
    }

    /**
     * 게시글 번호에 해당하는 게시글 조회 요청
     * 
     * @param boardNum
     * @param category
     * @param model
     * @return
     */
    @GetMapping("/boardDetail")
    public String boardDetail(@RequestParam(name = "boardNum") Long boardNum,
            @RequestParam(name = "category", defaultValue = "") String category,
            Model model) {
        BoardDTO dto = service.selectOne(boardNum);
        model.addAttribute("board", dto);
        model.addAttribute("category", category);
        return "board/boardDetail";
    }

}
