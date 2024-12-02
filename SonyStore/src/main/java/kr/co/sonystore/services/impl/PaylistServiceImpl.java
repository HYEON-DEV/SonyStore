package kr.co.sonystore.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sonystore.mappers.PaylistMapper;
import kr.co.sonystore.models.Paylist;
import kr.co.sonystore.services.PaylistService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class PaylistServiceImpl implements PaylistService {

    @Autowired
    private PaylistMapper paylistMapper;


    @Override
    public Paylist addItem(Paylist input) throws Exception {
        int rows = 0;

        try {
            rows = paylistMapper.insert(input);

            if (rows == 0) {
                throw new Exception("저장된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 저장에 실패했습니다.", e);
            throw e;
        }

        return paylistMapper.selectItem(input);
    }


    @Override
    public Paylist getItem(Paylist input) throws Exception {
        Paylist output = null;

        try {
            output = paylistMapper.selectItem(input);

            if (output == null) {
                throw new Exception("조회된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("결제 상품 목록 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }


    @Override
    public List<Paylist> getList(Paylist input) throws Exception {
        List<Paylist> output = null;

        try {
            output = paylistMapper.selectList(input);
        } catch (Exception e) {
            log.error("결제 상품 목록 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }



    @Override
    public int deleteByNoPayments(Paylist input) throws Exception {
        int rows = 0;

        try {
            rows = paylistMapper.deleteByNoPayments(input);
        } catch (Exception e) {
            log.error("데이터 삭제에 실패했습니다.", e);
            throw e;
        }

        return rows;
    }
    
}