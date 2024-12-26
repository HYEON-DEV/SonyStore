package kr.co.sonystore.services;

import java.util.List;

import kr.co.sonystore.models.TodaySale;

public interface TodaySaleService {
    
    /**
     * 일별 매출 집계 추가
     * @return 
     * @throws Exception
     */
    public int addItem() throws Exception;


    /**
     * 일별 매출 집계 조회
     * @return 조회된 데이터 리스트
     * @throws Exception
     */
    public List<TodaySale> getList(int day) throws Exception;
}
