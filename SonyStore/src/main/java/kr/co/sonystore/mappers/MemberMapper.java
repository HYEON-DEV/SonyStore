package kr.co.sonystore.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import kr.co.sonystore.models.Member;

@Mapper
public interface MemberMapper {
    
    /**
     * 회원 정보를 입력한다
     * @param input - 입력할 회원 정보에 대한 모델 객체
     * @return 입력된 데이터 수
     */
    @Insert("INSERT INTO members \n" + 
    "(email, userpw, username, gender, birthdate, \n" + 
    "phone, editdate, postcode, addr1, addr2, \n" + 
    "isout, logindate, regdate, isadmin) \n" + 
    "VALUES (#{email}, MD5(#{userpw}), #{username}, #{gender}, #{birthdate}, \n" + 
    "#{phone}, NOW(), #{postcode}, #{addr1}, #{addr2}, \n" + 
    "'N', null, NOW(), 'N')")
    @Options(useGeneratedKeys = true, keyProperty = "memberid", keyColumn = "memberid")
    public int insert(Member input);
}
