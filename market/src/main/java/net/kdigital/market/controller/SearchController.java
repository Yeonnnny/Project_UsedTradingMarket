package net.kdigital.market.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import net.kdigital.market.dto.BoardDTO;
import net.kdigital.market.service.SearchService;

@Controller
@RequiredArgsConstructor
public class SearchController {
    private final SearchService service;

    @GetMapping("/search/searchList")
    public String searchList() {
        return "search/searchList";
    }

    /**
     *  ajax에 의한 요청-> 전달받은 카테고리와 검색어에 해당하는 게시글 목록 반환 (thymeleaf에 적용함)
     * 
     * @param category
     * @return
     */
    @RequestMapping(value = "/search/searchByCategory", method = { RequestMethod.GET })
    public String searchByCategory(@RequestParam(name = "category", defaultValue = "total") String category,
            @RequestParam(name = "searchWord", defaultValue = "") String searchWord,
            Model model) {
        List<BoardDTO> dtoList = service.selectAllBySearching(category, searchWord);
        model.addAttribute("list", dtoList);
        return "/search/searchList::#result";
    }
}
