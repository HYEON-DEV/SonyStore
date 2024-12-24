package kr.co.sonystore.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

import kr.co.sonystore.models.Today_BestProduct;

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
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int insert(Today_BestProduct input);

    // 일별 집계 결과 검색
    @Select("SELECT id, title, date, cnt, DATE_FORMAT(date, '%a') as dayOfWeek " +
            "FROM today_bestproduct " +
            "WHERE DATE(date) >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) " +
            "ORDER BY DAYOFWEEK(date), cnt DESC")
    @Results(id = "today_bestproductMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "title", column = "title"),
            @Result(property = "date", column = "date"),
            @Result(property = "cnt", column = "cnt"),
            @Result(property = "dayOfWeek", column = "dayOfWeek")
    })
    public List<Today_BestProduct> selectDayList(Today_BestProduct input);

    
    // 주별 집계 결과 검색
    @Select("SELECT title, SUM(cnt) as cnt, " +
            "FLOOR((DAY(date) - 1) / 7) + 1 as weekOfMonth " +
            "FROM today_bestproduct " +
            "WHERE YEAR(date) = YEAR(CURDATE()) AND MONTH(date) = MONTH(CURDATE()) " +
            "GROUP BY weekOfMonth, title " +
            "ORDER BY weekOfMonth, cnt DESC")
    @Results(id = "weekly_bestproductMap", value = {
            @Result(property = "title", column = "title"),
            @Result(property = "cnt", column = "cnt"),
            @Result(property = "weekOfMonth", column = "weekOfMonth")
    })
    public List<Today_BestProduct> selectWeeklyList(Today_BestProduct input);
}