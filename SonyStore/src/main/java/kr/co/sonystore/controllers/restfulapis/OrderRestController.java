package kr.co.sonystore.controllers.restfulapis;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.sonystore.helpers.RestHelper;
import kr.co.sonystore.models.Member;
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
     * 장바구니 => 주문·결제 ( 결제 테이블에 데이터 추가 )
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
        @RequestParam("cartid") List<Integer> cartids,
        @SessionAttribute("memberInfo") Member memberInfo
    ) {
        Payment payment = new Payment();
        payment.setMemberid( memberInfo.getMemberid() );
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
     * 상품 상세 => 주문·결제 ( 결제 테이블에 데이터 추가 )
     */
    @PostMapping("/api/order_by_detail")
    public Map<String,Object> buyByDetail (
        @RequestParam("prodid") int prodid,
        @SessionAttribute("memberInfo") Member memberInfo,
        @RequestParam("total") int total,
        @RequestParam("thumbnail") String prodthumbnail,
        @RequestParam("title") String prodtitle,
        @RequestParam("color") String prodcolor,
        @RequestParam("count") int count,
        @RequestParam("price") int prodprice
    ) {
        Payment payment = new Payment();
        payment.setMemberid( memberInfo.getMemberid() );
        payment.setTotalcount(count);
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
        paylist.setProdid(prodid);
        paylist.setProdthumbnail(prodthumbnail);
        paylist.setProdtitle(prodtitle);
        paylist.setProdcolor(prodcolor);
        paylist.setCount(count);
        paylist.setProdprice(prodprice);

        Paylist outputPaylist = null;
        try {
            outputPaylist = paylistService.addItem(paylist);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        return restHelper.sendJson(data);
    }


    /**
     * 주문/결제 페이지 => 주문 완료 페이지 ( 결제 테이블 데이터 수정 )
     */
    @PutMapping("/api/order/complete") 
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
        @RequestParam("orderSheetNo") int payid,
        @RequestParam(value="cartid", required = false) List<Integer> cartids
        // HttpServletRequest httpRequest
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
        // String referer = httpRequest.getHeader("referer");
        try {
            outputPayment = paymentService.editItem(payment);
            if( cartids != null ) {
                cartService.deleteList(cartids);
            }
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String,Object> data = new LinkedHashMap<String,Object>();
        data.put("item", outputPayment);
        
        return restHelper.sendJson(data);
    }


    /**
     * 지정한 기간내의 주문 내역 조회
     */
    @GetMapping("/api/order_list_by_date")
    public Map<String,Object> order_list (
        @SessionAttribute("memberInfo") Member memberInfo
    ) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DATE);

        Paylist input = new Paylist();
        input.setMemberid(memberInfo.getMemberid());
        input.setFromdate(String.format("%04d-%02d-%02d 00:00:00", year-1, month, date));
        input.setTodate(String.format("%04d-%02d-%02d 23:59:59", year, month, date));

        List<Paylist> output = null;
        try {
            output = paylistService.getListByDate(input);
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String,Object> data = new LinkedHashMap<String,Object>();
        data.put("paylist", output);

        return restHelper.sendJson(data);
    }
}
