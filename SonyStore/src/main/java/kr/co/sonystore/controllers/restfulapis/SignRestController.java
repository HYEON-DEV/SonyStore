package kr.co.sonystore.controllers.restfulapis;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.sonystore.helpers.FileHelper;
import kr.co.sonystore.helpers.MailHelper;
import kr.co.sonystore.helpers.RegexHelper;
import kr.co.sonystore.helpers.RestHelper;
import kr.co.sonystore.helpers.UtilHelper;
import kr.co.sonystore.models.Member;
import kr.co.sonystore.services.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name="Sign API", description="회원 관련 API")
public class SignRestController {

    @Autowired
    private RestHelper restHelper;

    @Autowired
    private MemberService memberService;

    @Autowired
    private RegexHelper regexHelper;

    @Autowired
    private FileHelper fileHelper;

    @Autowired
    private UtilHelper utilHelper;

    @Autowired
    private MailHelper mailHelper;

    @PostMapping("/api/sign/login")
    public Map<String, Object> login(
            // 세션을 사용해야 하므로 request 객체가 필요하다
            HttpServletRequest request,
            @RequestParam("email") String email,
            @RequestParam("userpw") String userpw) {

        /** 1) 입력값에 대한 유효성 검사 */
        try {
            regexHelper.isValue(email, "아이디 이메일을 입력하세요");
            regexHelper.isValue(userpw, "비밀번호를 입력하세요");
        } catch (Exception e) {
            // 에러 로그를 기록하고, 사용자에게 안내 메시지를 전달한다.
            log.error("입력값 유효성검사 실패", e);
        }
        /** 2) 입력값을 Beans 객체에 저장 */

        Member input = new Member();
        input.setEmail(email);
        input.setUserpw(userpw);

        /** 3) 로그인 시도 */

        Member output = null;

        try {
            output = memberService.login(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        /** 4) 로그인에 성공했다면 회원 정보를 세션에 저장한다 */
        HttpSession session = request.getSession();
        session.setAttribute("memberInfo", output);

        /** 5) 로그인이 처리되었음을 응답한다 */
        return restHelper.sendJson();
    }

    @GetMapping("/api/sign/logout")
    public Map<String, Object> logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return restHelper.sendJson();
    }

    @PostMapping("/api/sign/find_id_pw")
    public Map<String, Object> findEmail(
            @RequestParam("username") String username,
            @RequestParam("phone") String phone) {
        Member input = new Member();
        input.setUsername(username);
        input.setPhone(phone);

        Member output = null;

        try {
            output = memberService.findEmail(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("member", output.getEmail());
        return restHelper.sendJson(data);

    }

    @PostMapping("/api/sign/find_pw")
    public Map<String, Object> findPw(
            @RequestParam("find_pw_email") String email,
            @RequestParam("find_pw_phone") String phone) {
        
        String newPassword = utilHelper.randomPassword(8);
        
        Member input = new Member();
        input.setEmail(email);
        input.setPhone(phone);
        input.setUserpw(newPassword);


        try {
            memberService.findUserPw(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        // 메일 템플릿 파일 경로
        ClassPathResource resource = new ClassPathResource("mail_templates/reset_pw.html");
        String mailTemplatePath = null;

        try {
            mailTemplatePath = resource.getFile().getAbsolutePath();
        } catch (IOException e) {
            return restHelper.serverError("메일 템플릿을 찾을 수 없습니다.");
        }

        // 메일 템플릿 파일 가져오기
        String template = null;

        try {
            template = fileHelper.readString(mailTemplatePath);
        } catch (Exception e) {
            return restHelper.serverError("메일 템플릿을 읽을 수 없습니다.");
        }

        // 메일 템플릿 안의 치환자 처리
        template = template.replace("{{password}}", newPassword);

        /** 3) 메일 발송 */
        String subject = "비밀번호가 재설정 되었습니다.";

        try {
            mailHelper.sendMail(email, subject, template);
        } catch (Exception e) {
            return restHelper.serverError("메일 발송에 실패했습니다.");
        }

        
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("member", input.getEmail());
        return restHelper.sendJson(data);
    }

    @PostMapping("/api/sign/sign_up_input")
    public Map<String, Object> join(
            @RequestParam("email") String email,
            @RequestParam("userpw") String userpw,
            @RequestParam("username") String username,
            @RequestParam("phone") String phone,
            @RequestParam("birthdate") String birthdate,
            @RequestParam("gender") String gender,
            @RequestParam("receiveemail") String receiveemail,
            @RequestParam("receivesms") String receivesms) {

        /** 1) 입력값에 대한 유효성 검사 */
        try {
            regexHelper.isValue(email, "아이디 이메일을 입력하세요");
            regexHelper.isEmail(email, "이메일 주소가 잘못되었습니다.");
            regexHelper.isValue(userpw, "비밀번호를 입력하세요");
            regexHelper.isValue(username, "이름를 입력하세요");
            regexHelper.isValue(phone, "핸드폰 번호를 입력하세요");
            regexHelper.isValue(birthdate, "생년월일를 입력하세요");
            regexHelper.isValue(gender, "성별를 선택하세요");

        } catch (Exception e) {
            // 에러 로그를 기록하고, 사용자에게 안내 메시지를 전달한다.
            log.error("입력값 유효성검사 실패", e);
        }
        /** 이메일 아이디 중복검사 */
        Member input = new Member();
        input.setEmail(email);

        try {
            memberService.isUniqueEmail(input);
        } catch (Exception e) {
            return restHelper.badRequest(e);
        }

        /** 휴대폰 번호 중복검사 */
        try {
            memberService.isUniquePhone(phone);
        } catch (Exception e) {
            return restHelper.badRequest(e);
        }

        /** 정보를 Service에 전달하기 위한 객체 구성 */
        Member member = new Member();
        member.setEmail(email);
        member.setUserpw(userpw);
        member.setUsername(username);
        member.setPhone(phone);
        member.setBirthdate(birthdate);
        member.setGender(gender);
        member.setReceiveemail(receiveemail);
        member.setReceivesms(receivesms);

        /** DB에 저장 */
        try {
            memberService.addMember(member);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }
        return restHelper.sendJson();
    }

    @PutMapping("/api/sign/modify_name")
    public Map<String, Object> modifyName(
            HttpServletRequest request,
            @SessionAttribute("memberInfo") Member memberInfo, // 현재 세션 정보 확인용
            @RequestParam("username") String username,
            @RequestParam("phone") String phone) {

        /* 입력값 유효성 검사 */
        try {
            regexHelper.isValue(username, "이름를 입력하세요");
        } catch (Exception e) {
            // 에러 로그를 기록하고, 사용자에게 안내 메시지를 전달한다.
            log.error("입력값 유효성검사 실패", e);
        }

        Member input = new Member();
        input.setMemberid(memberInfo.getMemberid());
        input.setEmail(memberInfo.getEmail());
        input.setUserpw(memberInfo.getUserpw());
        input.setGender(memberInfo.getGender());
        input.setBirthdate(memberInfo.getBirthdate());
        input.setEditdate(memberInfo.getEditdate());
        input.setPostcode(memberInfo.getPostcode());
        input.setAddr1(memberInfo.getAddr1());
        input.setAddr2(memberInfo.getAddr2());
        input.setReceiveemail(memberInfo.getReceiveemail());
        input.setReceivesms(memberInfo.getReceivesms());
        input.setUsername(username);
        input.setPhone(phone);

        try {
            memberService.modifyName(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        request.getSession().setAttribute("memberInfo", input);
        return restHelper.sendJson();
    }

    @PutMapping("/api/sign/modify_userpw")
    public Map<String, Object> modifyUserpw(
            HttpServletRequest request,
            @SessionAttribute("memberInfo") Member memberInfo, // 현재 세션 정보 확인용
            @RequestParam("userpw") String userpw,
            @RequestParam("newuserpw") String newuserpw) {

        /* 입력값 유효성 검사 */
        try {
            regexHelper.isValue(newuserpw, "새 비밀번호를 입력하세요");
        } catch (Exception e) {
            // 에러 로그를 기록하고, 사용자에게 안내 메시지를 전달한다.
            log.error("입력값 유효성검사 실패", e);
        }

        Member input = new Member();
        input.setMemberid(memberInfo.getMemberid());
        input.setUserpw(userpw);
        input.setNewuserpw(newuserpw);
        input.setEmail(memberInfo.getEmail());
        input.setUsername(memberInfo.getUsername());
        input.setPhone(memberInfo.getPhone());
        input.setGender(memberInfo.getGender());
        input.setBirthdate(memberInfo.getBirthdate());
        input.setEditdate(memberInfo.getEditdate());
        input.setPostcode(memberInfo.getPostcode());
        input.setAddr1(memberInfo.getAddr1());
        input.setAddr2(memberInfo.getAddr2());
        input.setReceiveemail(memberInfo.getReceiveemail());
        input.setReceivesms(memberInfo.getReceivesms());

        try {
            memberService.modifyUserpw(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        input.setUserpw(newuserpw);

        request.getSession().setAttribute("memberInfo", input);

        return restHelper.sendJson();
    }

    @PutMapping("/api/sign/modify_add_receive")
    public Map<String, Object> ModifyAddReceive(
            HttpServletRequest request, // 세션 갱신용
            @SessionAttribute("memberInfo") Member memberInfo, // 현재 세션 정보 확인용
            @RequestParam("postcode") String postcode,
            @RequestParam("addr1") String addr1,
            @RequestParam("addr2") String addr2,
            @RequestParam(value = "receiveemail", defaultValue = "N") String receiveemail,
            @RequestParam(value = "receivesms", defaultValue = "N") String receivesms) {

        Member input = new Member();
        input.setMemberid(memberInfo.getMemberid());
        input.setPostcode(postcode);
        input.setAddr1(addr1);
        input.setAddr2(addr2);
        input.setReceiveemail(receiveemail);
        input.setReceivesms(receivesms);

        input.setUserpw(memberInfo.getUserpw());
        input.setNewuserpw(memberInfo.getNewuserpw());
        input.setEmail(memberInfo.getEmail());
        input.setUsername(memberInfo.getUsername());
        input.setPhone(memberInfo.getPhone());
        input.setGender(memberInfo.getGender());
        input.setBirthdate(memberInfo.getBirthdate());
        input.setEditdate(memberInfo.getEditdate());

        try {
            memberService.ModifyAddReceive(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        request.getSession().setAttribute("memberInfo", input);
        return restHelper.sendJson();
        // 아래 회원탈퇴한것처럼 input에 세션값을 하나하나 넣어서 파라미터로 보내는게 아니라
        // session에 input에 변경한 것들을 넣어주고 service에 memberInfo를 파라미터로 보내주면 간단하게 수정 가능할 것 같다
    }

    @DeleteMapping("/api/sign/member_secession")
    public Map<String, Object> out(
            HttpServletRequest request,
            @SessionAttribute("memberInfo") Member memberInfo,
            @RequestParam("userpw") String userpw) {

        // 세션으로부터 추출한 Member 객체에 입력받은 비밀번호를 넣어준다
        memberInfo.setUserpw(userpw);

        try {
            memberService.out(memberInfo);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        // 로그아웃을 위해 세션을 삭제한다
        HttpSession session = request.getSession();
        session.invalidate();

        return restHelper.sendJson();
    }
}
