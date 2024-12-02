package kr.co.sonystore.controllers.restfulapis;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.sonystore.helpers.RestHelper;
import kr.co.sonystore.models.Paylist;
import kr.co.sonystore.models.Payment;
import kr.co.sonystore.services.CartService;
import kr.co.sonystore.services.PaylistService;
import kr.co.sonystore.services.PaymentService;


@RestController
public class OrderRestController {
 
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaylistService paylistService;

    @Autowired
    private CartService cartService;

    @Autowired
    private RestHelper restHelper;


    /**
     * 장바구니 => 주문·결제 ( 결제 테이블에 장바구니 데이터 추가 )
     */
    @PostMapping("/api/order")
    public Map<String,Object> buy (
        @RequestParam("totalcount") int totalcount,
        @RequestParam("total") int total,
        @RequestParam("prodid") List<Integer> prodid,
        @RequestParam("prodthumbnail") List<String> prodthumbnail,
        @RequestParam("prodtitle") List<String> prodtitle,
        @RequestParam("prodcolor") List<String> prodcolor,
        @RequestParam("count") List<Integer> count,
        @RequestParam("prodprice") List<Integer> prodprice,
        @RequestParam("cartid") List<Integer> cartids
    ) {
        Payment payment = new Payment();
        payment.setMemberid(2);
        payment.setTotalcount(totalcount);
        payment.setTotal(total);

        Payment outputPayment = null;
        try {
            outputPayment = paymentService.addItem(payment);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String,Object> data = new LinkedHashMap<String,Object>();
        data.put("item", outputPayment);

        Paylist paylist = new Paylist();
        paylist.setPayid(payment.getPayid());
        
        Paylist outputPaylist = null;

        try {
            for ( int i=0; i<prodid.size(); i++) {
                paylist.setProdid(prodid.get(i));
                paylist.setProdthumbnail(prodthumbnail.get(i));
                paylist.setProdtitle(prodtitle.get(i));
                paylist.setProdcolor(prodcolor.get(i));
                paylist.setCount(count.get(i));
                paylist.setProdprice(prodprice.get(i));
                outputPaylist = paylistService.addItem(paylist);
            }
        } catch (Exception e) {
            return restHelper.serverError(e);
        }
        
        data.put("cartids", cartids);

        return restHelper.sendJson(data);
    }


    /**
     * 주문/결제 페이지 => 주문 완료 페이지 ( 결제 테이블의 주문 정보 수정 )
     */
    @PutMapping("/api/order/complete/{payid}") 
    public Map<String,Object> order_complete (
        @RequestParam("ordername") String ordername,
        @RequestParam("orderemail") String orderemail,
        @RequestParam("orderphone") String orderphone,
        @RequestParam("receivername") String receivername,
        @RequestParam("receiverphone") String receiverphone,
        @RequestParam("postcode") String postcode,
        @RequestParam("addr1") String addr1,
        @RequestParam("addr2") String addr2,
        @RequestParam("request") String request,
        @RequestParam("dlvdate") String dlvdate,
        @RequestParam("payoption") String payoption,
        @PathVariable("payid") int payid
    ) {
        Payment payment = new Payment();
        payment.setPayid(payid);
        payment.setOrdername(ordername);
        payment.setOrderemail(orderemail);
        payment.setOrderphone(orderphone);
        payment.setReceivername(receivername);
        payment.setReceiverphone(receiverphone);
        payment.setPostcode(postcode);
        payment.setAddr1(addr1);
        payment.setAddr2(addr2);
        payment.setRequest(request);
        payment.setDlvdate(dlvdate);
        payment.setPayoption(payoption);

        Payment outputPayment = null;
        try {
            outputPayment = paymentService.editItem(payment);

        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String,Object> data = new LinkedHashMap<String,Object>();
        data.put("item", outputPayment);
        
        return restHelper.sendJson(data);
    }
}
