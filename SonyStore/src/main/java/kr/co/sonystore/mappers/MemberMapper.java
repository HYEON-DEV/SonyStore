package kr.co.sonystore.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.sonystore.models.Member;

@Mapper
public interface MemberMapper {

        /**
         * 회원 정보를 입력한다
         * 
         * @param input - 입력할 회원 정보에 대한 모델 객체
         * @return 입력된 데이터 수
         */
        @Insert("INSERT INTO members \n" +
                        "(email, userpw, username, gender, birthdate, \n" +
                        "phone, editdate, postcode, addr1, addr2, \n" +
                        "isout, logindate, regdate, isadmin, \n" +
                        "receiveemail, receivesms) \n" +
                        "VALUES (#{email}, #{userpw}, #{username}, #{gender}, #{birthdate}, \n" +
                        "#{phone}, NOW(), #{postcode}, #{addr1}, #{addr2}, \n" +
                        "'N', null, NOW(), 'N', \n" +
                        "#{receiveemail}, #{receivesms})")
        @Options(useGeneratedKeys = true, keyProperty = "memberid", keyColumn = "memberid")
        public int insert(Member input);

        /**
         * 회원 정보를 수정한다
         * 
         * @param input - 수정할 데이터에 대한 모델 객체
         * @return 수정된 데이터 수
         */
        @Update("<script>" +
                        "update members set " +
                        // memberid 수정 x
                        // email 수정 x
                        // 신규 비밀번호가 입력된 경우만 update절에 추가함
                        /* 임시 비밀번호 세팅 신규 비밀번호 입력된 경우로 변경해야함 */
                        "userpw=#{userpw}, " +
                        // "<if test='newuserpw != null and newuserpw != \"\"'>userpw =
                        // #{newuserpw},</if>\n" +
                        "username=#{username}, " +
                        "gender=#{gender}, " +
                        "birthdate=#{birthdate}, " +
                        "phone=#{phone}, " +
                        "editdate=now(), " +
                        "postcode=#{postcode}, " +
                        "addr1=#{addr1}, " +
                        "addr2=#{addr2}, " +
                        "receiveemail=#{receiveemail}, " +
                        "receivesms=#{receivesms} " +
                        "<where>" +
                        // 세션의 일련번호와 입력한 비밀번호가 일치할 경우만 수정
                        "memberid=#{memberid} " + // and userpw = #{userpw}" +
                        "</where>" +
                        "</script>")
        public int update(Member input);

        @Delete("delete from members where memberid=#{memberid}")
        public int delete(Member input);

        @Select("select " +
                "memberid, email, userpw, username, gender, " +
                "birthdate, phone, editdate, postcode, addr1, " +
                "addr2, isout, logindate, regdate, isadmin, " +
                "receiveemail, receivesms " +
                "FROM members " +
                "WHERE memberid = #{memberid}")
        @Results(id = "memberMap", value = {
                        @Result(property = "memberid", column = "memberid"),
                        @Result(property = "email", column = "email"),
                        @Result(property = "userpw", column = "userpw"),
                        @Result(property = "username", column = "username"),
                        @Result(property = "gender", column = "gender"),
                        @Result(property = "birthdate", column = "birthdate"),
                        @Result(property = "phone", column = "phone"),
                        @Result(property = "editdate", column = "editdate"),
                        @Result(property = "postcode", column = "postcode"),
                        @Result(property = "addr1", column = "addr1"),
                        @Result(property = "addr2", column = "addr2"),
                        @Result(property = "isout", column = "isout"),
                        @Result(property = "logindate", column = "logindate"),
                        @Result(property = "regdate", column = "regdate"),
                        @Result(property = "isadmin", column = "isadmin"),
                        @Result(property = "receiveemail", column = "receiveemail"),
                        @Result(property = "receivesms", column = "receivesms")
        })
        Member selectMember(Member input);

        // 이메일 및 휴대폰 중복검사
        @Select("<script>" + //
                        "select count(*) from members\n" + //
                        "<where>\n" + //
                        "<if test='email != null'>email = #{email}</if>\n" + //
                        "<if test='phone != null'>phone = #{phone}</if>\n" + //
                        "<if test='memberid != 0'>and memberid != #{memberid}</if>\n" + //
                        "</where>\n" + //
                        "</script>")
        public int selectCount(Member input);

        // 이메일(아이디) 찾기
        @Select("select email from members " +
                        "where username = #{username} and phone = #{phone}")
        @ResultMap("memberMap")
        Member findEmail(Member input);



        // 비밀번호 찾기 (이메일과 휴대폰 본인인증)
        @Update("update members set userpw = #{userpw} " +
                "where email = #{email} and phone = #{phone}")
        @ResultMap("memberMap")
        int findUserPw(Member input);

        // 로그인
        @Select("select \n" +
                        "memberid, email, userpw, username, gender, " +
                        "birthdate, phone, editdate, postcode, addr1, " +
                        "addr2, isout, logindate, regdate, isadmin, " +
                        "receiveemail, receivesms " +
                        "from members \n" +
                        "where email = #{email} and userpw = #{userpw}")
        @ResultMap("memberMap")
        public Member login(Member input);

        @Update("update members set logindate = now() where memberid = #{memberid}")
        public int updateLoginDate(Member input);

        // 회원탈퇴
        @Update("update members \n" +
                        "set isout = 'Y', editdate = now() \n" +
                        "where memberid = #{memberid} and userpw = #{userpw} and isout = 'N'")
        public int out(Member input);

        // 탈퇴한 회원 중 탈퇴 후 7일이 지난 회원 삭제
        @Delete("delete from members \n" +
                        "where isout='Y' and \n" +
                        "editdate < date_add(now(), interval -7 day)")
        public int deleteOutMembers();

        // 이름 변경
        @Update("update members \n" +
                        "set username = #{username}, editdate = now() \n" +
                        "where memberid = #{memberid} and phone = #{phone}")
        public int modifyName(Member input);

        // 비밀번호 변경
        @Update("update members \n" +
                        "set userpw = #{newuserpw}, editdate = now() \n" +
                        "where memberid = #{memberid} and userpw = #{userpw}")
        public int modifyUserpw(Member input);

        // 주소, 알림설정 변경
        @Update("update members \n" +
                        "set postcode = #{postcode}, addr1 = #{addr1}, addr2 = #{addr2}, receiveemail = #{receiveemail}, receivesms = #{receivesms}, editdate = now() \n"
                        +
                        "where memberid = #{memberid}")
        public int ModifyAddReceive(Member input);
}
