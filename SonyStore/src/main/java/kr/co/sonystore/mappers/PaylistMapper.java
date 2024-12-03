package kr.co.sonystore.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import kr.co.sonystore.models.Paylist;

@Mapper
public interface PaylistMapper {

    /**
     * 결제 상품 목록에 구매할 상품 정보를 추가한다
     * @param input - 입력할 결제 상품 정보에 대한 모델 객체
     * @return 추가된 데이터 수
     */
    @Insert(
        "INSERT INTO paylist ( \n" + 
            "payid, prodid, prodthumbnail, prodtitle, \n" +
            "prodcolor, count, prodprice ) \n" +
        "VALUES ( \n" +
            "#{payid}, #{prodid}, #{prodthumbnail}, #{prodtitle}, \n" + 
            "#{prodcolor}, #{count}, #{prodprice} )" )
    @Options(useGeneratedKeys = true, keyProperty = "paylistid", keyColumn = "paylistid")
    public int insert(Paylist input);

    
    /**
     * 결제 상품을 단일 조회한다
     * @param input - 조회할 결제 상품 일련번호를 가진 모델 객체
     * @return 조회된 데이터
     */
    @Select(
        "SELECT \n" +
            "payid, paylistid, prodid, prodthumbnail, prodtitle, \n" + 
            "prodcolor, prodprice, count, prodprice*count AS sum \n" + 
        "FROM paylist \n" + 
        "WHERE paylistid = #{paylistid}"
    )
    @Results(id="paylistMap", value={
        @Result(property="payid", column="payid"),
        @Result(property="paylistid", column="paylistid"),
        @Result(property="prodid", column="prodid"),
        @Result(property="prodthumbnail", column="prodthumbnail"),
        @Result(property="prodtitle", column="prodtitle"),
        @Result(property="prodcolor", column="prodcolor"),
        @Result(property="prodprice", column="prodprice"),
        @Result(property="count", column="count"),
        @Result(property="sum", column="sum")
    })
    public Paylist selectItem(Paylist input);


    /**
     * 결제 상품 목록을 조회한다
     * @param input - 조회할 결제 내역 일련번호를 가진 모델 객체
     * @return 조회된 데이터 목록
     */
    @Select(
        "SELECT \n" +
            "payid, paylistid, prodid, prodthumbnail, prodtitle, \n" + 
            "prodcolor, prodprice, count, prodprice*count AS sum \n" + 
        "FROM paylist \n" + 
        "WHERE payid = #{payid} \n" +
        "ORDER BY paylistid"
    )
    @ResultMap("paylistMap")
    public List<Paylist> selectList(Paylist input);


    /**
     * 미결제 상품의 결제 상품 목록을 삭제한다
     * @param input - 삭제할 결제 상품 정보에 대한 모델 객체
     * @return 삭제된 데이터 수
     */
    @Delete(
        "DELETE FROM paylist WHERE payid = #{payid}"
    )
    public int deleteByNoPayments(Paylist input);



    /**
     * 지정 기간 내의 주문 상품을 조회한다
     * @param input
     * @return
     */
    @Select (
        "SELECT \n" +
        "pl.payid, pm.date, pm.status, \n" +
        "CONCAT( DATE_FORMAT(pm.date, '%Y%m%d%H%i%s'), LPAD(pl.payid,6,'0') ) AS orderno, \n" +
        "paylistid, prodid, prodthumbnail, prodtitle, \n" +
        "prodcolor, prodprice, count, prodprice*count AS sum \n" +
        "FROM paylist pl \n" +
        "INNER JOIN payments pm ON pl.payid = pm.payid \n" +
        "WHERE memberid = #{memberid} AND \n" +
        "pm.date BETWEEN #{fromdate} AND #{todate} \n" +
        "ORDER BY paylistid"
    )
    @ResultMap("paylistMap")
    public List<Paylist> selectListByDate(Paylist input);
}
