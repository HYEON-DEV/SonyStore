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
        input.setEmail("chang@gmail.com");
        input.setUserpw("qwe123");
        input.setUsername("홍창기");
        input.setGender("M");
        input.setBirthdate("19930501");
        input.setPhone("01011112222");
        input.setPostcode("12345");
        input.setAddr1("서울특별시 잠실동");
        input.setAddr2("123");
        
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

}
