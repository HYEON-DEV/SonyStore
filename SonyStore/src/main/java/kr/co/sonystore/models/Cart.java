package kr.co.sonystore.models;

import lombok.Data;

@Data
public class Cart {
    private int cartid;
    private int count;
    private int memberid;
    private int prodid;
}
