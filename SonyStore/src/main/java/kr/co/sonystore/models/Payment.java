package kr.co.sonystore.models;

import lombok.Data;

@Data
public class Payment {
    private int payid;             // 일련번호               
    private String date;           // 결제날짜          NULL YES   
    private String status;         // 처리상태
    private int memberid;          // 회원의 일련번호    NULL YES 
    private String ordername;      // 주문자 이름
    private String orderemail;     // 주문자 이메일                       
    private String orderphone;     // 주문자 휴대폰번호                  
    private String receivername;   // 수령인 이름       NULL YES 
    private String receiverphone;  // 수령인 휴대폰번호  NULL YES   
    private String postcode;       // 우편번호          NULL YES
    private String addr1;          // 검색된 주소       NULL YES      
    private String addr2;          // 상세 주소         NULL YES  
    private String request;        // 배송 요청 사항    NULL YES   
    private String dlvdate;        // 배송일            NULL YES
    private int totalcount;        // 전체 수량         
    private int total;             // 총 결제금액            
    private String payoption;      // 결제방법           
    private String insertdate;     // 구매 희망 일시 
    private String paycheck;       // 결제유무       
    private String orderno;        // 주문번호  
}
