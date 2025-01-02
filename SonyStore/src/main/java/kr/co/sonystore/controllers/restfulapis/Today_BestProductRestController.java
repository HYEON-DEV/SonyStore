package kr.co.sonystore.controllers.restfulapis;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.sonystore.helpers.RestHelper;
import kr.co.sonystore.models.Today_BestProduct;
import kr.co.sonystore.services.Today_BestProductService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@Tag(name="Best Product API", description="주간, 월간 인기 상품 순위 API" )
@RestController
public class Today_BestProductRestController {

    @Autowired
    private Today_BestProductService today_bestProductService;
    @Autowired
    private RestHelper restHelper;

    
    @GetMapping("/api/today_best_products")
    @Operation(summary="인기 상품 순위 조회", description="주간, 월간 상품 판매량 순위를 조회한다.")
    @ApiResponses( value={
        @ApiResponse(responseCode="200", description="판매량 순위 조회 성공"),
        @ApiResponse(responseCode="500", description="판매량 순위 조회 실패")
    })
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
