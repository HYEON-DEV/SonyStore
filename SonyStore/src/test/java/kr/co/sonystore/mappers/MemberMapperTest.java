package kr.co.sonystore.mappers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import kr.co.sonystore.models.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MemberMapperTest {
    
    @Autowired
    private MemberMapper memberMapper;

    @Test
    @DisplayName("회원 추가 테스트")
    void insertMember() {
        Member input = new Member();
        input.setEmail("jinsu@gmail.com");
        input.setUserpw("Qwer1234@");
        input.setUsername("박진수");
        input.setGender("M");
        input.setBirthdate("19930501");
        input.setPhone("01011112222");
        input.setPostcode("12345");
        input.setAddr1("서울특별시 잠실동");
        input.setAddr2("123");
        input.setReceiveemail("Y");
        input.setReceivesms("Y");
        
        int output = memberMapper.insert(input);
        
        log.debug("output: " + output);
        log.debug("new id: " + input.getMemberid());
    }

    @Test
    @DisplayName("회원 수정 테스트")
    void updateMemberTest(){
        Member input = new Member();
        input.setMemberid(1);
        input.setUserpw("1234");
        input.setUsername("박진수");
        input.setGender("F");
        input.setBirthdate("1996-09-05");
        input.setPhone("01033064205");
        input.setPostcode("21016");
        input.setAddr1("경기도 수원시");
        input.setAddr2("우만동");

        

        int output = memberMapper.update(input);

        log.debug("output: " + output);
    }

    @Test
    @DisplayName("회원 삭제 테스트")
    void deleteMemberTest(){
        Member input = new Member();
        input.setMemberid(3);

        int output = memberMapper.delete(input);

        log.debug("output: " + output);
    }

    @Test
    @DisplayName("회원 단일 조회 테스트")
    void selectMemberTest(){
        Member input = new Member();
        input.setMemberid(1);

        Member output = memberMapper.selectMember(input);
        log.debug("output: " + output);
    }

    @Test
    @DisplayName("이메일 중복검사 테스트")
    void isUniqueEmailTest(){
        Member input = new Member();
        input.setEmail("wlsn4205@gmail.com");

        int output = memberMapper.selectCount(input);
        log.debug("중복된 이메일 개수: " + output);
    }

    @Test
    @DisplayName("이메일 찾기 테스트")
    void findEmailTest(){
        Member input = new Member();
        input.setUsername("박진수");
        input.setPhone("01033064205");

        Member output = memberMapper.findEmail(input);
        log.debug("등록된 이메일: " + output);
    }

    @Test
    @DisplayName("비밀번호 찾기 테스트")
    void findPwTest(){
        Member input = new Member();
        input.setEmail("chang@gmail.com");
        input.setPhone("01033064205");

        Member output = memberMapper.findUserPw(input);
        log.debug("비밀번호: " + output);
    }

    @Test
    @DisplayName("로그인 정보 테스트")
    void loginTest(){
        Member input = new Member();
        input.setEmail("chang@gmail.com");
        input.setUserpw("1234");

        Member output = memberMapper.login(input);
        log.debug("로그인 완료: " + output);
    }

    @Test
    @DisplayName("마지막 로그인 시각 최신화 테스트")
    void updateLoginDateTest(){
        Member input = new Member();
        input.setMemberid(1);
        
        int output = memberMapper.updateLoginDate(input);
        log.debug("로그인 시간 수정완료 데이터 수: " + output);
    }

    @Test
    @DisplayName("회원탈퇴 테스트")
    void outTest(){
        Member input = new Member();
        input.setMemberid(2);
        input.setUserpw("123123");
        
        int output = memberMapper.out(input);
        log.debug("삭제된 데이터 수: " + output);
    }

    @Test
    @DisplayName("1분 지난 회원의 탈퇴 테스트")
    void deleteOutMembersTest(){
    
        int output = memberMapper.deleteOutMembers();
        log.debug("삭제된 데이터 수: " + output);
    }

    @Test
    @DisplayName("주소 알림 테스트")
    void AddReceiveTest(){
        Member input = new Member();
        input.setMemberid(16);
        input.setPostcode("11111");
        input.setAddr1("거기");
        input.setAddr2("여기");
        input.setReceiveemail("N");
        input.setReceivesms("N");
    
        int output = memberMapper.ModifyAddReceive(input);
        log.debug("삭제된 데이터 수: " + output);
    }
}
