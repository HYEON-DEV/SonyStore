package kr.co.sonystore.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;

import kr.co.sonystore.models.Cart;


@Mapper
public interface CartMapper {
    
    /**
     * 장바구니에 상품을 추가한다
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
     * 장바구니 상품의 수량을 입력한 만큼 증가시킨다 
     * @param input
     * @return
     */
    @Update (
        "<script> \n" +
        "UPDATE carts SET count = count + #{count} \n" +
        "WHERE memberid = #{memberid} AND prodid = #{prodid} \n" + 
        "<if test = 'color != null'> AND color = #{color} </if> \n" +
        "</script>"
    )
    int updateCount(Cart input);


    /**
     * 장바구니 상품의 수량을 변경한다
     * @param input - 변경할 장바구니 정보에 대한 모델 객체
     * @return 수정된 데이터 수
     */
    @Update ( "UPDATE carts SET count = #{count} WHERE cartid = #{cartid}" )
    public int update(Cart input);
    

    /**
     * 장바구니 목록을 조회한다
     * @param input - 조회할 장바구니 정보에 대한 모델 객체
     * @return 조회된 장바구니 목록
     */
    @Select (
        // "<script> " +
        "SELECT cartid, memberid, c.prodid, title, i.colorid, \n" + 
        "c.color, filepath, price, count, price*count AS sum \n" +
        "FROM carts c \n" +
        "INNER JOIN products p ON c.prodid = p.prodid \n" +
        "LEFT JOIN colors clr ON c.prodid = clr.prodid AND c.color = clr.color \n" +
        "LEFT JOIN images i ON \n" + 
            "c.prodid = i.prodid AND (clr.colorid = i.colorid OR clr.colorid IS NULL) \n" + 
            "AND i.thumbnail = 'Y' \n" +
        "WHERE memberid = #{memberid} \n" +
        "ORDER BY cartid"
        // + "</script>"
    )        
    @Results ( id="cartMap", value = {
        @Result ( property="cartid", column="cartid" ),
        @Result ( property="memberid", column="memberid" ),
        @Result ( property="prodid", column="prodid" ),
        @Result ( property="filepath", column="filepath" ),
        @Result ( property="title", column="title" ),
        @Result ( property="color", column="color" ),
        @Result ( property="price", column="price" ),
        @Result ( property="count", column="count" ),
        @Result ( property="thumbnail", column="thumbnail" )
    } )
    public List<Cart> selectList(Cart input);


    /**
     * 장바구니의 단일행을 조회한다
     * @param input - 조회할 장바구니 정보에 대한 모델 객체
     * @return 조회된 장바구니 정보
     */
    @Select (
        "SELECT cartid, memberid, c.prodid, title, i.colorid, \n" + 
        "c.color, filepath, price, count, price*count AS sum \n" +
        "FROM carts c \n" +
        "INNER JOIN products p ON c.prodid = p.prodid \n" +
        "LEFT JOIN colors clr ON c.prodid = clr.prodid AND c.color = clr.color \n" +
        "LEFT JOIN images i ON \n" + 
            "c.prodid = i.prodid AND (clr.colorid = i.colorid OR clr.colorid IS NULL) \n" + 
            "AND i.thumbnail = 'Y' \n" +
        "WHERE cartid = #{cartid} \n"
    ) @ResultMap("cartMap")
    public Cart selectItem(Cart input);


    /**
     * 장바구니에 중복된 상품이 있는지 조회한다
     * @param input - 검사할 장바구니 정보에 대한 모델 객체
     * @return 조회된 데이터 수
     */
    @Select (
        "<script> \n" +
        "SELECT COUNT(*) FROM carts \n" + 
        "WHERE memberid = #{memberid} AND prodid = #{prodid} \n" + 
        "<if test = 'color != null'> AND color = #{color} </if> \n" + 
        "</script>"
    )
    public int selectCount(Cart input);


    /**
     * 장바구니에서 단일 상품을 삭제한다
     * @param input - 삭제할 장바구니 정보에 대한 모델 객체
     * @return 삭제된 데이터 수
     */
    @Delete ( "DELETE FROM carts WHERE cartid = #{cartid}" )
    public int delete(Cart input);

 
    /**
     * 장바구니에서 다중 상품을 삭제한다
     * @param cartidList - 삭제할 장바구니 번호를 담고 있는 리스트
     * @return 삭제된 데이터 수
     */
    @Delete ( 
        "<script> \n" +
        "DELETE FROM carts WHERE cartid IN \n" + 
        "<foreach item='cartid' collection='cartidList' open='(' separator=',' close=')' > \n" +
        "#{cartid} \n" +
        "</foreach> \n" + 
        "</script>" )
    public int deleteList(@Param("cartidList") List<Integer> cartidList);
}


