package kr.co.sonystore.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Today_BestProductMapper {
    // 집계 결과를 테이블에 바로 넣기
    @Insert("INSERT INTO today_bestproduct (title, date, cnt) " +
            "SELECT pl.prodtitle, DATE(pm.date) as dt, COUNT(*) AS cnt " +
            "FROM paylist pl " +
            "INNER JOIN payments pm ON pl.payid = pm.payid " +
            "WHERE DATE(pm.date) = DATE(DATE_ADD(NOW(), INTERVAL 0 DAY)) " +
            "GROUP BY DATE(pm.date), pl.prodtitle " +
            "ORDER BY cnt DESC " +
            "LIMIT 10")
    public int insert();
}