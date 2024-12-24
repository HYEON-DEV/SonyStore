package kr.co.sonystore.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sonystore.mappers.Today_BestProductMapper;
import kr.co.sonystore.models.Today_BestProduct;
import kr.co.sonystore.services.Today_BestProductService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class Today_BestProductServiceImpl implements Today_BestProductService{
    @Autowired
    private Today_BestProductMapper today_BestProductMapper;

    @Override
    public int insert(Today_BestProduct input) throws Exception {

        int result = 0;

        try {
            result = today_BestProductMapper.insert(input);
        }  catch (Exception e) {
            log.error("데이터 조회에 실패했습니다.", e);
            throw e;
        }

        return result;
    }

    @Override
    public List<Today_BestProduct> selectDayList(Today_BestProduct input) throws Exception {
        List<Today_BestProduct> result = null;

        try {
            result = today_BestProductMapper.selectDayList(input);
        } catch (Exception e) {
            log.error("데이터 조회에 실패했습니다.", e);
            throw new Exception("데이터 조회에 실패했습니다.");
        }

        return result;
    }

    @Override
    public List<Today_BestProduct> selectWeeklyList(Today_BestProduct input) throws Exception {
        List<Today_BestProduct> result = null;

        try {
            result = today_BestProductMapper.selectWeeklyList(input);
        } catch (Exception e) {
            log.error("데이터 조회에 실패했습니다.", e);
            throw new Exception("데이터 조회에 실패했습니다.");
        }

        return result;
    }
    
}
