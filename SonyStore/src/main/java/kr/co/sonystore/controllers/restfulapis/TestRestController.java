package kr.co.sonystore.controllers.restfulapis;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.sonystore.helpers.FileHelper;
import kr.co.sonystore.helpers.RestHelper;
import kr.co.sonystore.models.Product;
import kr.co.sonystore.services.TestService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class TestRestController {

    @Autowired
    private RestHelper restHelper;

    @Autowired
    private TestService testService;

    @Autowired
    private FileHelper fileHelper;

    
    @GetMapping("/api/test")
    public Map<String,Object> test( Model model) {
        
        Product input = new Product();
        input.setProdid(3);

        Product output = null;
        try {
            output = testService.getItem(input);
            output.setDetailimage1(fileHelper.getUrl(output.getDetailimage1()));
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String,Object> data = new LinkedHashMap<String,Object>();
        data.put("item",output);
        
        return restHelper.sendJson(data);
    }
    
}
