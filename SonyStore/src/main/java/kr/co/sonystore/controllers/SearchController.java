package kr.co.sonystore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.sonystore.helpers.WebHelper;
import kr.co.sonystore.helpers.Pagination;
import kr.co.sonystore.models.Product;

@Controller
public class SearchController {

    @Autowired
    private WebHelper webHelper;

    @GetMapping("/search/search_result")
    public String searchResult(Model model,
        @RequestParam(value = "keyword", required = false) String keyword,
        @RequestParam(value = "page", defaultValue = "1") int nowPage) {

        int totalCount = 0; // 전체 게시글 수
        int listcount = 9; // 한 페이지당 표시할 목록 수

        Pagination pagination = null;

        Product input = new Product();

        input.setTitle(keyword);
        input.setType1(keyword);
        input.setType2(keyword);
        input.setType3(keyword);
        

        List<Product> Products = null;



        // try {
            // totalCount = searchService.getCount(input);
        //     //페이지 번호 계산 ==> 계산 결과가 로그로 출력될 것이다.
        //     pagination = new Pagination(nowPage, totalCount, listcount, pageCount);

        //     Professor.setOffset(pagination.getOffset());
        //     Professor.setListCount(pagination.getListCount());
  
        //     students = studentService.getList(input);
        //     professors = professorService.getList(input2);
        //     departments = departmentService.getList(input3);
        // } catch (ServiceNoResultException e) {
        //     webHelper.serverError(e);
        //     return null;
        // } catch (Exception e) {
        //     webHelper.serverError(e);
        //     return null;
        // }
        
        // model.addAttribute("students", students);
        // model.addAttribute("departments", professors);
        // model.addAttribute("professors", departments);

        // model.addAttribute("keyword", keyword);
        // model.addAttribute("pagination", pagination);

        return "search/search_result";
    }
}
