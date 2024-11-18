package kr.parkjaehan.sonystore.mappers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sonystore.mappers.MemberMapper;
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
}
