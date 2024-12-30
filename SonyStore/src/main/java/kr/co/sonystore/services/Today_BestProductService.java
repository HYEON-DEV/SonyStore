package kr.co.sonystore.services;
import java.util.List;

import kr.co.sonystore.models.Today_BestProduct;

public interface Today_BestProductService {
    
    public int insert(Today_BestProduct input) throws Exception;

    public List<Today_BestProduct> selectWeeklyList(Today_BestProduct input) throws Exception;

    public List<Today_BestProduct> selectMonthlyList(Today_BestProduct input) throws Exception;

}
