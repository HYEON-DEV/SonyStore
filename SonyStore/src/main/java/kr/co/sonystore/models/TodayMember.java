package kr.co.sonystore.models;

import lombok.Data;

@Data
public class TodayMember {
    private int id;        // 일련번호     
    private String date;   // 날짜    
    private int count;     // 가입자 수
}
