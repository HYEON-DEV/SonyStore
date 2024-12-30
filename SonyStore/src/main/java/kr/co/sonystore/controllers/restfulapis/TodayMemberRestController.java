package kr.co.sonystore.controllers.restfulapis;

import org.springframework.web.bind.annotation.RestController;

import kr.co.sonystore.helpers.RestHelper;
import kr.co.sonystore.models.TodayMember;
import kr.co.sonystore.models.Today_BestProduct;
import kr.co.sonystore.services.TodayService;
import kr.co.sonystore.services.Today_BestProductService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class TodayMemberRestController {

    @Autowired
    private TodayService todayService;
    @Autowired
    private RestHelper restHelper;

    
    @GetMapping("/api/week_member")
    public Map<String, Object> selectWeekMember() throws Exception {
        
        List<TodayMember> output = null;
        
        try {
            output = todayService.selectWeekMemberCount();
        } catch (Exception e) {
            return restHelper.serverError(e);
        }
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("item", output);

        return restHelper.sendJson(data);
    }

    @GetMapping("/api/month_member")
    public Map<String, Object> selectMonthMember() throws Exception {
        
        List<TodayMember> output = null;
        
        try {
            output = todayService.selectMonthMemberCount();
        } catch (Exception e) {
            return restHelper.serverError(e);
        }
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("item", output);

        return restHelper.sendJson(data);
    }
    
}
