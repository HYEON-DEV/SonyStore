package kr.co.sonystore.models;

import lombok.Data;

@Data
public class Cart {

    private int cartid;    // 일련번호          
    private int count;     // 수량               
    private int memberid;  // 회원의 일련번호 
    private int prodid;    // 상품의 일련번호   
    private String color;  // 상품의 색상  
    
    private String filepath;    // 상품 이미지 경로 (조인을 통해 조회된 값)
    private String title;       // 상품 이름 (조인을 통해 조회된 값)
    private int price;          // 상품 가격 (조인을 통해 조회된 값)
    private int sum;            // 상품 가격 * 수량 
}
