package kr.co.sonystore.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.sonystore.models.Cart;


@Mapper
public interface CartMapper {
    
    /**
     * 장바구니 정보를 추가한다
     * @param input - 입력할 장바구니 정보에 대한 모델 객체
     * @return 입력된 데이터 수
     */
    @Insert(
        "INSERT INTO carts \n" + 
        "(count, memberid, prodid, color) \n" + 
        "VALUES ( #{count}, #{memberid}, #{prodid}, #{color} )" )
    @Options(useGeneratedKeys = true, keyProperty = "cartid", keyColumn = "cartid")
    public int insert(Cart input);
    

    /**
     * 장바구니 목록을 조회한다
     * @param input - 조회할 장바구니 정보에 대한 모댈 객체
     * @return 입력된 데이터 수
     */
    @Select ( 
        "SELECT cartid, memberid, c.prodid, filepath, title, \n" +
        "color, price, count, price*count AS sum \n" +
        "FROM carts c \n" +
        "INNER JOIN products p ON c.prodid = p.prodid \n" +
        "INNER JOIN images i ON c.prodid = i.prodid \n" +
        "WHERE memberid = #{memberid} \n" +
        "AND thumbnail='Y' " )
    public List<Cart> selectList(Cart input);


    /**
     * 장바구니의 수량을 수정한다
     * @param input - 수정할 장바구니 정보에 대한 모델 객체
     * @return 수정된 데이터 수
     */
    @Update ( "UPDATE carts SET count = #{count} WHERE cartid = #{cartid}" )
    public int update(Cart input);


    /**
     * 장바구니 단일 삭제
     * @param input
     * @return
     */
    @Delete ( "DELETE FROM carts WHERE cartid = #{cartid}" )
    public int delete(Cart input);


    @Delete ( 
        "<SCRIPT> \n" +
        "DELETE FROM carts WHERE cartid IN \n" + 
        "<FOREACH ITEM='cartid' COLLECTION='cartids' OPEN='(' SEPARATOR=',' CLOSE=')' > \n" +
        "#{cartid} \n" +
        "</FOREACH> \n" + 
        "</SCRIPT>" )
    public int deleteList(@Param("cartids") List<Integer> cartids);
}
