package kr.co.sonystore.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import kr.co.sonystore.models.Cart;


@Mapper
public interface CartMapper {
    
    /**
     * 장바구니 정보를 입력한다
     * @param input - 입력할 장바구니 정보에 대한 모델 객체
     * @return 입력된 데이터 수
     */
    @Insert("INSERT INTO carts \n" + 
    "(count, memberid, prodid) \n" + 
    "VALUES ( #{count}, #{memberid}, #{prodid} )" )
    @Options(useGeneratedKeys = true, keyProperty = "cartid", keyColumn = "cartid")
    public int insert(Cart input);
}
