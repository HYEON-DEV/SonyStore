package kr.co.sonystore.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sonystore.mappers.PaylistMapper;
import kr.co.sonystore.mappers.PaymentMapper;
import kr.co.sonystore.models.Paylist;
import kr.co.sonystore.models.Payment;
import kr.co.sonystore.services.PaymentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private PaylistMapper paylistMapper;


    @Override
    public Payment addItem(Payment input) throws Exception {
        int rows = 0;

        try {
            rows = paymentMapper.insert(input);

            if (rows == 0) {
                throw new Exception("저장된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 저장에 실패했습니다.", e);
            throw e;
        }

        return paymentMapper.selectItemNoPaid(input);
    }
    

    @Override
    public Payment editItem(Payment input) throws Exception {
        int rows = 0;

        try {
            rows = paymentMapper.update(input);

            if (rows == 0) {
                throw new Exception("수정된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 수정에 실패했습니다.", e);
            throw e;
        }

        return paymentMapper.selectItem(input);
    }


    @Override
    public Payment getItem(Payment input) throws Exception {
        Payment output = null;

        try {
            output = paymentMapper.selectItem(input);

            if (output == null) {
                throw new Exception("결제내역 조회된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("결제내역 데이터 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }


    @Override
    public Payment getItemNoPaid(Payment input) throws Exception {
        Payment output = null;

        try {
            output = paymentMapper.selectItemNoPaid(input);

            if (output == null) {
                throw new Exception("미결제 내역 조회된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("미결제 내역 데이터 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }


    @Override
    public List<Payment> getDlvList(Payment input) throws Exception {
        List<Payment> output = null;

        try {
            output = paymentMapper.selectDlvList(input);    
            
            if(output.isEmpty()) {
                log.debug("등록된 배송지가 없습니다");
            }
        } catch (Exception e) {
            log.error("배송지 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }


    @Override
    public List<Payment> deleteNoPayments() throws Exception {
        List<Payment> output = null;
        Paylist paylist = new Paylist();

        try {
            // 미결제상품 결제내역 조회
            output = paymentMapper.selectNoPayments();

            if(output==null || output.isEmpty()) {
                log.debug("조회된 데이터가 없습니다.");
                return output;
            } 

            // 미결제상품 (paylist) 삭제
            for (Payment item : output) {
                try {
                    paylist.setPayid(item.getPayid());
                    paylistMapper.deleteByNoPayments(paylist);
                } catch (Exception e) {
                    log.error("미결제상품 삭제에 실패했습니다.", e);
                    throw e;
                }
            }

            // 미결제상품 결제내역 (payment) 삭제
            try {
                paymentMapper.deleteNoPayments();
                log.debug("미결제상품 결제내역 삭제 완료");
            } catch (Exception e) {
                log.error("미결제상품 결제내역 삭제에 실패했습니다.", e);
                throw e;
            }

        } catch (Exception e) {
            throw new Exception("미결제상품 결제내역 삭제 처리에 실패했습니다.", e);
        }

        return output;
    }


    @Override
    public int getCountPayComplete(Payment input) throws Exception {
        int rows = 0;
        try {
            rows = paymentMapper.selectCountPayComplete(input);
        } catch(Exception e) {
            log.error("결제완료 데이터 조회에 실패했습니다.", e);
            throw e;
        }
        
        return rows;
    }


    @Override
    public List<Payment> getPayListByDate(Payment input) throws Exception {
        List<Payment> output = null;
        try {
            output = paymentMapper.selectListByDate(input);
        } catch (Exception e) {
            log.error("주문 내역 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }
    
}