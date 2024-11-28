package kr.co.sonystore.models;

import lombok.Data;

@Data
public class Paylist {
    private int paylistid;          // 구매한 상품의 일련번호         
    private int payid;              // 결제내역의 일련번호                
    private int prodid;             // 상품의 일련번호          NULL YES           
    private String prodthumbnail;   // 상품의 대표 이미지 경로  NULL YES   
    private String prodtitle;       // 상품의 이름             
    private String prodcolor;       // 구매한 상품의 색상       NULL YES         
    private int count;              // 상품의 수량                        
    private int prodprice;          // 상품의 가격            
    private int sum;                // 상품의 가격 * 수량          
}