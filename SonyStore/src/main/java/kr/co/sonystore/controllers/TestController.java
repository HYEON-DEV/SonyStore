package kr.co.sonystore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.sonystore.helpers.FileHelper;
import kr.co.sonystore.helpers.WebHelper;
import kr.co.sonystore.models.Product;
import kr.co.sonystore.services.TestService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private WebHelper webHelper;

    @Autowired
    private FileHelper fileHelper;

    @GetMapping("/test_image")
    public String view( Model model ) {
        Product input = new Product();
        input.setProdid(3);

        Product output = null;
        try {
            output = testService.getItem(input);
            output.setDetailimage1(fileHelper.getUrl(output.getDetailimage1()));
        } catch (Exception e) {
            webHelper.serverError(e);
        }

        model.addAttribute("product", output);

        return "/tests/test";
    }
}
