package kr.co.sonystore.models;

import lombok.Data;

@Data
public class Today_BestProduct {
    private int id;
    private String title;
    private int date;
    private int cnt;
}
