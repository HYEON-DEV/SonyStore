package kr.co.sonystore.controllers.restfulapis;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpServletResponse;
import kr.co.sonystore.helpers.FileHelper;
import kr.co.sonystore.helpers.MailHelper;
import kr.co.sonystore.helpers.RestHelper;
import kr.co.sonystore.models.Member;
import kr.co.sonystore.models.Paylist;
import kr.co.sonystore.models.Payment;
import kr.co.sonystore.services.CartService;
import kr.co.sonystore.services.PaylistService;
import kr.co.sonystore.services.PaymentService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
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

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${mailhelper.sender.email}")
    private String senderEmail;

    @Autowired
    private FileHelper fileHelper;

    @Autowired
    private MailHelper mailHelper;


    /**
     * 주문·결제 ( 결제 테이블에 데이터 추가 )
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
        @RequestParam(value="cartid", required=false) List<Integer> cartids,
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
        
        if ( cartids != null ) {
            data.put("cartids", cartids);
        }

        return restHelper.sendJson(data);
    }



    /**
     * 주문/결제 페이지 => 주문 완료 페이지 ( 결제 테이블 데이터 수정 )
     */
    @SuppressWarnings("null")
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
        @RequestParam(value="cartid", required = false) List<Integer> cartids,
        @RequestParam("prodtitle") List<String> prodtitles,
        @RequestParam("prodcount") List<Integer> prodcounts,
        HttpServletResponse response
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
            if( cartids != null ) {
                cartService.deleteList(cartids);
            }
            
        } catch (Exception e) {
            return restHelper.serverError(e);
        }

        Map<String,Object> data = new LinkedHashMap<String,Object>();
        data.put("item", outputPayment);


        /**
         * 결제 완료 메일 발송
         */

        String mailTemplatePath = "src/main/resources/templates/order_result.html";

        String template = null;

        try {
            template = fileHelper.readString(mailTemplatePath);
        } catch (Exception e) {
            log.error("메일 템플릿을 읽을 수 없습니다", e);
        }

        template = template.replace("{{userName}}", ordername);
        template = template.replace("{{orderNumber}}", outputPayment.getOrderno());
        template = template.replace("{{qty}}", String.valueOf(outputPayment.getTotalcount()));
        template = template.replace("{{orderDate}}", outputPayment.getDate());
        template = template.replace("{{orderPrice}}", String.valueOf(outputPayment.getTotal()));

        String products = "";
        for ( int i=0; i<prodtitles.size(); i++) {
            // String.format("<p>%s</p>", prodtitles.get(i)));
            products += String.format("%s (%s개)", prodtitles.get(i), prodcounts.get(i));
            if ( i+1 < prodtitles.size() ) {
                products += ", ";
            }
        }
        template = template.replace("{{productName}}", products);

        String subject = ordername + "님, 주문이 완료되었습니다.";

        try {
            mailHelper.sendMail( orderemail, subject, template ); 
        } catch (Exception e) {
            log.error("메일 발송에 실패했습니다", e);
        }
        log.debug("메일이 발송되었습니다.");

        
        return restHelper.sendJson(data);
    }


    /**
     * 지정한 기간내의 주문 내역 조회
     */
    @GetMapping("/api/order_list_by_date")
    public Map<String,Object> order_list (
        @SessionAttribute("memberInfo") Member memberInfo,
        @RequestParam(value="fromdate", required=false) String fromdate,
        @RequestParam(value="todate", required=false) String todate

    ) {
        Paylist input = new Paylist();
        input.setMemberid(memberInfo.getMemberid());

        if( fromdate==null || todate==null ) {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int date = cal.get(Calendar.DATE);
    
            input.setFromdate(String.format("%04d-%02d-%02d 00:00:00", year-1, month, date));
            input.setTodate(String.format("%04d-%02d-%02d 23:59:59", year, month, date));    
        } else {
            input.setFromdate(fromdate + " 00:00:00");
            input.setTodate(todate + " 23:59:59");
        }
        
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
