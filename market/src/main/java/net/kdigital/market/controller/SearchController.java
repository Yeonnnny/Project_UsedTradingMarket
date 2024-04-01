package net.kdigital.market.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/searchList")
    public String searchList() {
        return "search/searchList";
    }
    

    /**
     * 전달받은 카테고리와 검색어에 해당하는 게시글 목록 요청
     * 
     * @param category
     * @return
     */
    @GetMapping("/searchByCategory")
    @ResponseBody
    public List<BoardDTO> searchByCategory(@RequestParam(name = "category", defaultValue = "total") String category,
                            @RequestParam(name = "searchWord", defaultValue = "") String searchWord, 
                            Model model) {
        List<BoardDTO> dtoList = service.selectAllBySearching(category,searchWord);

        return dtoList;
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
