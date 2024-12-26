package kr.co.sonystore.controllers.restfulapis;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.sonystore.helpers.RestHelper;
import kr.co.sonystore.models.TodaySale;
import kr.co.sonystore.services.TodaySaleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name="Sale API", description="주간, 월간 총 매출 관련 API" )
public class TodaySaleRestController {

    @Autowired
    private TodaySaleService todaySaleService;

    @Autowired
    private RestHelper restHelper;


    @GetMapping("/api/today_sales/day")
    @Operation(summary="총 매출 조회", description="매출을 조회한다.")
    @ApiResponses( value={
        @ApiResponse(responseCode="200", description="총 매출 조회 성공"),
        @ApiResponse(responseCode="500", description="총 매출 조회 실패")
    })
    @Parameters({
        @Parameter(name="day", description = "조회할 일자", required = false)
    })
    public Map<String,Object> getWeeklySale(
        @RequestParam(value = "day", defaultValue = "7", required = false) int day
    ) {
        List<TodaySale> output = null;
        try {
            output = todaySaleService.getList(day);
        } catch(Exception e) {
            return restHelper.serverError(e);
        }

        Map<String,Object> data = new LinkedHashMap<String,Object>();
        data.put("item", output);

        return restHelper.sendJson(data);
    }
}
