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
    "where date between DATE(DATE_ADD(NOW(), INTERVAL -7 DAY)) and DATE(DATE_ADD(NOW(), INTERVAL -1 DAY)) ")
    public List<TodayMember> selectWeekMemberCount();


    // 한달간의 주간 통계
    // @Select("select date, SUM(count) as count " +
    // "from (select concat( " +
    // "DATE_FORMAT(DATE_SUB(`date`, INTERVAL (DAYOFWEEK(`date`)-1) DAY), '%Y/%m/%d'), " +
    // "' - ', " +
    // "DATE_FORMAT(DATE_SUB(`date`, INTERVAL (DAYOFWEEK(`date`)-7) DAY), '%Y/%m/%d') " +
    // ") as date, count from today_member group by date, count order by date desc limit 21) as today_member " +
    // "group by date " +
    // "order by date asc")
    // public List<TodayMember> selectMonthMemberCount();

    /**
     * dayofweek는 요일을 숫자로 반환한다. 1은 일요일이고 7은 토요일이 기준이다.
     * 어떤 요일이든 그 주의 일요일을 구하기 위해선 DAYOFWEEK() - 1을 해주고 그 일자에서 결과
     * 를 빼면 그 주의 일요일(시작일)을 구할 수 있다.
     * 그 주의 토요일(종료일)을 구하기 위해선 DAYOFWEEK() - 7을 해주고 그 일자에서 결과를 빼
     * 주면 그 주의 토요일을 구할 수 있다.
    */

    // 한달간의 주간 통계 (데이터는 오늘 이후 까지 있어도 현재까지만 나오도록 수정한 SQL문)
    @Select("select date, SUM(count) as count " +
    "from (select concat( " +
    "DATE_FORMAT(DATE_SUB(`date`, INTERVAL (DAYOFWEEK(`date`)-1) DAY), '%Y/%m/%d'), " +
    "' - ', " +
    "DATE_FORMAT(DATE_SUB(`date`, INTERVAL (DAYOFWEEK(`date`)-7) DAY), '%Y/%m/%d') " +
    ") as date, count from today_member " +
    "where `date` <= CURDATE() " +
    "group by date, count order by date desc limit 21) as today_member " +
    "group by date " +
    "order by date asc")
    public List<TodayMember> selectMonthMemberCount();
}
