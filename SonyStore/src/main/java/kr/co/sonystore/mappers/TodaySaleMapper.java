package kr.co.sonystore.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.co.sonystore.models.TodaySale;


@Mapper
public interface TodaySaleMapper {
    

    /**
     * 일별 매출 집계 추가
     * @return 추가된 데이터 수
     */
    @Insert(
        "<script> " +
        "INSERT INTO today_sales (date, total)"+
        "<choose>" +
        "<when test='exists'>" +        
            "SELECT DATE(p.date) AS dt, SUM(p.total) AS total "+
                "FROM payments p"+
            "WHERE paycheck = 'Y' AND" +
                "DATE(p.date) = DATE(DATE_ADD(NOW(), INTERVAL -1 DAY))"+
            "GROUP BY dt" +
        "</when>" +
        "<otherwise>" + 
            "VALUES (DATE(NOW()), 0)" +
        "</otherwise>" +
        "</choose>" +
        "</script>"
    )
    public int insert();



    /**
     * 한 달간 일별 매출 집계 조회
     * @return 조회된 데이터 리스트
     */
    @Select(
        "SELECT id, date, total FROM today_sales " +
        "WHERE date BETWEEN DATE(DATE_ADD(NOW(), INTERVAL #{day} DAY)) " +
                    "AND DATE(DATE_ADD(NOW(), INTERVAL -1 DAY)) " +
        "ORDER BY date"
    )
    public List<TodaySale> selectList(int day);



}
