package kr.co.sonystore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.sonystore.helpers.WebHelper;
import kr.co.sonystore.models.Product;
import kr.co.sonystore.services.SearchService;

@Controller
public class SearchController {

    @Autowired
    private WebHelper webHelper;
    
    @Autowired
    private SearchService searchService;

    @GetMapping("/search/search_result")
    public String searchResult(Model model,
        @RequestParam(value = "keyword", required = false) String keyword,
        @RequestParam(value = "page", defaultValue = "1") int nowPage) {

        int totalCount = 0; // 전체 게시글 수
        // int listcount = 9; // 한 페이지당 표시할 목록 수

        Product input = new Product();

        input.setKeyword(keyword);

        try{
            totalCount = searchService.getCount(input);
        } catch (Exception e) {
            webHelper.serverError(e);
            return null;
        }
        
        model.addAttribute("totalCount", totalCount);
        // model.addAttribute("listcount", listcount);
        model.addAttribute("keyword", keyword);
        // model.addAttribute("products", products);

        return "search/search_result";
    }
}
