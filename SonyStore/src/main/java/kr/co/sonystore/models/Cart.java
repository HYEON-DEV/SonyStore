package kr.co.sonystore.models;

import lombok.Data;

@Data
public class Cart {

    private int cartid;    // 일련번호          
    private int count;     // 수량               
    private int memberid;  // 회원의 일련번호 
    private int prodid;    // 상품의 일련번호   
    private String color;  // 상품의 색상     
}
