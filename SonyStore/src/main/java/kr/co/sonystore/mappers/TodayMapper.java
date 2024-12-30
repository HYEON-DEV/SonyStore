package kr.co.sonystore.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.co.sonystore.models.TodayMember;


@Mapper
public interface TodayMapper {
    
    // 가입인원수 집계 후 테이블에 넣기
    @Insert("insert into today_member (date, count) " +
            "select DATE(regdate) date, COUNT(*) count " +
            "from members " +
            "where DATE(regdate) = DATE(DATE_ADD(NOW(), INTERVAL -1 DAY)) " +
            "group by date")
    public int insertNewMemberCount();

    // 7간 일간 통계
    @Select("select date, count " +
    "from today_member " +
    "where date >= DATE(DATE_ADD(NOW(), INTERVAL -7 DAY))")
    public List<TodayMember> selectWeekMemberCount();

    // 한달간의 주간 통계
    @Select("select date, SUM(count) as count " +
    "from (select concat( " +
    "DATE_FORMAT(DATE_SUB(`date`, INTERVAL (DAYOFWEEK(`date`)-1) DAY), '%Y/%m/%d'), " +
    "' - ', " +
    "DATE_FORMAT(DATE_SUB(`date`, INTERVAL (DAYOFWEEK(`date`)-7) DAY), '%Y/%m/%d') " +
    ") as date, count from today_member group by date, count order by date desc limit 21) as today_member " +
    "group by date " +
    "order by date asc")
    public List<TodayMember> selectMonthMemberCount();

    
}
