package kr.co.sonystore.controllers.restfulapis;

import org.springframework.web.bind.annotation.RestController;

import kr.co.sonystore.helpers.RestHelper;
import kr.co.sonystore.models.Today_BestProduct;
import kr.co.sonystore.services.Today_BestProductService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class Today_BestProductRestController {

    @Autowired
    private Today_BestProductService today_bestProductService;
    @Autowired
    private RestHelper restHelper;

    
    @GetMapping("/api/today_best_products")
    public Map<String, Object> getTodayBestProductList() throws Exception {

        Today_BestProduct input = new Today_BestProduct();
        List<Today_BestProduct> output1 = null;
        List<Today_BestProduct> output2 = null;
        try {
            output1 = today_bestProductService.selectWeeklyList(input);
            output2 = today_bestProductService.selectMonthlyList(input);
           
        } catch (Exception e) {
            return restHelper.serverError(e);
        }
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("weekly", output1);
        data.put("monthly", output2);
        return restHelper.sendJson(data);
    }
    
}
