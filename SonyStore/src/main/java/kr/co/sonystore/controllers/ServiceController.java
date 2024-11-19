package kr.co.sonystore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServiceController {
    
    /** FAQ 공지사항 페이지 */
    @GetMapping("/notice")
    public String faqNotice() {
        return "/services/faq_notice";
    }

    /** 공지사항 상세보기 페이지 */
    @GetMapping("/notice_detail/")
    public String noticeDetail() {
        return "/services/notice_detail";
    }
}
