package kr.co.sonystore.controllers.restfulapis;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.sonystore.helpers.RestHelper;
import kr.co.sonystore.models.TodayMember;
import kr.co.sonystore.services.TodayService;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RestController
@Tag(name="newMember API", description="주간, 월간 신규 가입자 수 API" )
public class TodayMemberRestController {

    @Autowired
    private TodayService todayService;
    @Autowired
    private RestHelper restHelper;

    
    @GetMapping("/api/week_member")
    @Operation(summary="주간 일별 신규 회원 조회", description="신규회원을 조회한다.")
    @ApiResponses( value={
    @ApiResponse(responseCode="200", description="주간 신규 회원 조회 성공"),
    @ApiResponse(responseCode="500", description="주간 신규 회원 조회 실패")
    })
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
    @Operation(summary="월간 주별 신규 회원 조회", description="신규회원을 조회한다.")
    @ApiResponses( value={
        @ApiResponse(responseCode="200", description="월간 신규 회원 조회 성공"),
        @ApiResponse(responseCode="500", description="월간 신규 회원 조회 실패")
        })
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
