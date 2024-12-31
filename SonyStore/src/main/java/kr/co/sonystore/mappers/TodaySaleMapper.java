package kr.co.sonystore.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.co.sonystore.models.TodaySale;


@Mapper
public interface TodaySaleMapper {


    /**
     * 전날 매출 수 조회
     * @return 조회된 데이터
     */
    @Select(
        "SELECT COUNT(*) FROM payments WHERE DATE(date) = DATE(DATE_ADD(NOW(), INTERVAL -1 DAY))" 
    )
    public int countYesterdaySale();
    

    /**
     * 일별 매출 집계 추가 (매출이 있을 경우)
     * @return 추가된 데이터 수
     */
    @Insert(
        "INSERT INTO today_sales (date, total) \n"+
            "SELECT DATE(p.date) AS dt, SUM(p.total) AS total \n"+
                "FROM payments p \n"+
            "WHERE paycheck = 'Y' AND \n" +
                "DATE(p.date) = DATE(DATE_ADD(NOW(), INTERVAL -1 DAY)) \n"+
            "GROUP BY dt"
    )
    public int insert();


    /**
     * 일별 매출 집계 추가 (매출이 없을 경우)
     * @return
     */
    @Insert(
        "INSERT INTO today_sales (date, total) \n" + 
            "VALUES (DATE(DATE_ADD(NOW(), INTERVAL -1 DAY)), 0)"
    )
    public int insertZero();



    /**
     * 일별 매출 집계 조회
     * @return 조회된 데이터 리스트
     */
    @Select(
        "SELECT id, DATE_FORMAT(date,'%Y/%m/%d') AS date, total FROM today_sales \n" +
            "WHERE date BETWEEN DATE(DATE_ADD(NOW(), INTERVAL #{day} DAY)) \n" +
                "AND DATE(DATE_ADD(NOW(), INTERVAL -1 DAY)) \n" +
            "ORDER BY date"
    )
    public List<TodaySale> selectList(int day);



    /**
     * 전날 매출 집계 조회
     * @return 조회된 데이터
     */
    @Select(
        "SELECT id, date, total FROM today_sales \n" +
            "WHERE date = DATE(DATE_ADD(NOW(), INTERVAL -1 DAY))"
    )
    public TodaySale selectItem();
}
