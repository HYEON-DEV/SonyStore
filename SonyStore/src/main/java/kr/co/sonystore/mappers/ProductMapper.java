package kr.co.sonystore.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

import kr.co.sonystore.models.Product;

@Mapper
public interface ProductMapper {
    
    /**
     * 상품 정보를 입력한다
     * @param input - 입력할 상품 정보에 대한 모델 객체
     * @return 입력된 데이터 수
     */
    @Insert("INSERT INTO products " + 
    "(title, proddesc, price, type1, type2, type3, " + 
    "date, detailimage1, detailimage2, youtube, detailgif, " + 
    "detailspec, soldout, sale, event) " + 
    "VALUES ( #{title}, #{proddesc}, #{price}, #{type1}, #{type2}, #{type3}, " + 
    "#{date}, #{detailimage1}, #{detailimage2}, #{youtube}, #{detailgif}, " + 
    "#{detailspec}, #{soldout}, #{sale}, #{event} )")
    @Options(useGeneratedKeys = true, keyProperty = "prodid", keyColumn = "prodid")
    public int insert(Product input);

    /**
     * 상품 정보를 업데이트한다
     * @param input - 업데이트할 상품 정보에 대한 모델 객체
     * @return 업데이트된 데이터 수
     */
    @Update("UPDATE products SET " +
    "title = #{title}, " +
    "proddesc = #{proddesc}, " +
    "price = #{price}, " +
    "type1 = #{type1}, " +
    "type2 = #{type2}, " +
    "type3 = #{type3}, " +
    "date = #{date}, " +
    "detailimage1 = #{detailimage1}, " +
    "detailimage2 = #{detailimage2}, " +
    "youtube = #{youtube}, " +
    "detailgif = #{detailgif}, " +
    "detailspec = #{detailspec}, " +
    "soldout = #{soldout}, " +
    "sale = #{sale}, " +
    "event = #{event} " +
    "WHERE prodid = #{prodid}")
    public int updateProduct(Product input);

    
}