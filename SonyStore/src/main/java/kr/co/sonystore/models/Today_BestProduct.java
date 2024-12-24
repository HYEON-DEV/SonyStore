package kr.co.sonystore.models;

import lombok.Data;

@Data
public class Today_BestProduct {
    private int id;
    private String title;
    private String date;
    private int cnt;
    private String dayOfWeek; // 요일
    private int weekOfMonth; // 주차
}
