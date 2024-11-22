package kr.co.sonystore.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 회원 정보를 수정한다
     * @param input -   수정할 데이터에 대한 모델 객체
     * @return  수정된 데이터 수
     */
    @Update("<script>" +
            "update members set " +
            // memberid 수정 x
            // email 수정 x
            // 신규 비밀번호가 입력된 경우만 update절에 추가함
            /* 임시 비밀번호 세팅 신규 비밀번호 입력된 경우로 변경해야함 */
            "userpw=#{userpw}, " +
            // "<if test='newuserpw != null and newuserpw != \"\"'>userpw = #{newuserpw},</if>\n" + 
            "username=#{username}, " +
            "gender=#{gender}, " + 
            "birthdate=#{birthdate}, " + 
            "phone=#{phone}, " + 
            "editdate=now(), " + 
            "postcode=#{postcode}, " + 
            "addr1=#{addr1}, " + 
            "addr2=#{addr2} " + 
            "<where>" +
            // 세션의 일련번호와 입력한 비밀번호가 일치할 경우만 수정
            "memberid=#{memberid} " + // and userpw = #{userpw}" +
            "</where>" +
            "</script>")
    public int update(Member input);

    @Delete("delete from members where memberid=#{memberid}")
    public int delete(Member input);

    @Select("select " + 
    "memberid, email, userpw, username, gender, "+
    "birthdate, phone, editdate, postcode, addr1, " + 
    "addr2, isout, logindate, regdate, isadmin " +
    "FROM Member " + 
    "WHERE memberid = #{memberid}")
    @Results(id = "MemberMap", value = {
        @Result(property = "memberid", column = "memberid"),
        @Result(property = "email", column = "email"),
        @Result(property = "userpw", column = "userpw"),
        @Result(property = "username", column = "username"),
        @Result(property = "gender", column = "gender"),
        @Result(property = "birthdate", column = "birthdate"),
        @Result(property = "", column = ""),
        @Result(property = "", column = ""),
        @Result(property = "", column = ""),
        @Result(property = "", column = ""),
        @Result(property = "", column = ""),
        @Result(property = "", column = ""),
        @Result(property = "", column = "")

    })
    Member selectMember(Member input);
}
