package kr.co.sonystore.models;

import java.io.Serializable;

import lombok.Data;

// 세션에 저장할 클래스 타입은 Serializable 인터페이스를 상속해야 한다
// Serializable : "객체직렬화"
@Data
public class Member implements Serializable {
    private int memberid;
    private String email;
    private String userpw;
    private String username;
    private String gender;
    private String birthdate;
    private String phone;
    private String editdate;
    private String postcode;
    private String addr1;
    private String addr2;
    private String isout;
    private String logindate;
    private String regdate;
    private String isadmin;

    // 재로그인 필요
    private String newuserpw;   // 회원정보 수정에서 사용할 신규 비밀번호
}
