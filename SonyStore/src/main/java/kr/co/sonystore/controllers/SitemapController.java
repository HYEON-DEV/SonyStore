package kr.co.sonystore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SitemapController {
    @GetMapping("/sitemap")
    public String siteMap() {
        return "sitemap/site_map";
    }
}
