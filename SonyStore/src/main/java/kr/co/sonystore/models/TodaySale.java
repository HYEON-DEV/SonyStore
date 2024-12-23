package kr.co.sonystore.models;

import lombok.Data;

@Data
public class TodaySale {
    private int id;        // 일련번호     
    private String date;   // 날짜    
    private int total;     // 매출      
}
