package kr.co.sonystore.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;


@Mapper
public interface TodaySaleMapper {
    
    @Insert(
        "<script>" +
        "INSERT INTO today_sales (date, total)"+
        "<choose>" +
            "<when test='exists'>" +        
                "SELECT DATE(p.date) AS dt, SUM(p.total) AS total "+
                    "FROM payments p"+
                "WHERE paycheck = 'Y' AND" +
                    "DATE(p.date) = DATE(NOW())"+
                "GROUP BY dt" +
            "</when>" +
            "<otherwise>" + 
                "VALUES (DATE(NOW()), 0)" +
            "</otherwise>" +
        "</choose>" +
        "</script>"
    )
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int insert();
}
