package kr.co.sonystore.controllers.restfulapis;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.sonystore.helpers.RestHelper;
import kr.co.sonystore.helpers.UtilHelper;
import kr.co.sonystore.models.Member;
import kr.co.sonystore.services.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SignRestController {

    @Autowired
    private RestHelper restHelper;

    @Autowired
    private MemberService memberService;

    @Autowired
    private UtilHelper utilHelper;

    @PostMapping("/api/sign/login")
    public Map<String,Object>login(
        // 세션을 사용해야 하므로 request 객체가 필요하다
        HttpServletRequest request,
        @RequestParam("email") String email,
        @RequestParam("userpw") String userpw){

        /** 1) 입력값에 대한 유효성 검사 */
        // 여기서는 생략

        /** 2) 입력값을 Beans 객체에 저장 */

        Member input = new Member();
        input.setEmail(email);
        input.setUserpw(userpw);

        /** 3) 로그인 시도 */

        Member output = null;

        try{
            output = memberService.login(input);
        }catch(Exception e){
            return restHelper.serverError(e);
        }

        /** 4) 로그인에 성공했다면 회원 정보를 세션에 저장한다 */
        HttpSession session = request.getSession();
        session.setAttribute("memberInfo", output);

        /** 5) 로그인이 처리되었음을 응답한다 */
        return restHelper.sendJson();
    }

    @GetMapping("/api/sign/logout")
    public Map<String,Object> logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return restHelper.sendJson();
    }
}
