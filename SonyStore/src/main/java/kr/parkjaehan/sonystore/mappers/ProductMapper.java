package kr.parkjaehan.sonystore.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import kr.parkjaehan.sonystore.models.Product;

@Mapper
public interface ProductMapper {
    
    /**
     * 상품 정보를 입력한다
     * @param input - 입력할 상품 정보에 대한 모델 객체
     * @return 입력된 데이터 수
     */
    @Insert("INSERT INTO products \n" + 
    "(title, `desc`, price, type1, type2, type3, \n" + 
    "date, detailimage1, detailimage2, youtube, detailgif, \n" + 
    "detailspec, soldout, sale, event, colorid ) \n" + 
    "VALUES ( #{title}, #{desc}, #{price}, #{type1}, #{type2}, #{type3}, \n" + 
    "#{date}, #{detailimage1}, #{detailimage2}, #{youtube}, #{detailgif}, \n" + 
    "#{detailspec}, 'N', 'N', #{event}, #{colorid} )" )
    @Options(useGeneratedKeys = true, keyProperty = "prodid", keyColumn = "prodid")
    public int insert(Product input);
}
