package kr.co.sonystore.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;

import kr.co.sonystore.models.Color;
import kr.co.sonystore.models.Image;
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
    public int update(Product input);

    /**
     * 상품 정보를 삭제한다
     * @param input - 삭제할 상품 정보에 대한 모델 객체
     * @return 삭제된 데이터 수
     */
    @Delete("DELETE FROM products WHERE prodid = #{prodid}")
    public int delete(Product input);

    // 상품을 삭제하기 전에 상품에 소속된 색상 데이터를 삭제
    @Delete("DELETE FROM colors WHERE prodid = #{prodid}")
    void deleteColorsByProductId(Product input);

    // 상품을 삭제하기 전에 상품에 소속된 이미지 데이터를 삭제
    @Delete("DELETE FROM images WHERE prodid = #{prodid}")
    void deleteImagesByProductId(Product input);

    @Select("SELECT * FROM images WHERE prodid = #{prodid}")
    List<Image> selectImagesByProductId(int prodid);

    @Select("SELECT * FROM colors WHERE prodid = #{prodid}")
    @Results({
        @Result(property = "colorid", column = "colorid"),
        @Result(property = "color", column = "color"),
        @Result(property = "pcolor", column = "pcolor")
    })
    List<Color> selectColorsByProductId(int prodid);

    /**
     * 단일행 조회를 위한 기능 정의
     * @param input - 조회할 상품 정보에 대한 모델 객체
     * @return 조회된 데이터 정보
     */
    @Select("SELECT p.prodid, p.title, p.proddesc, p.price, p.type1, p.type2, p.type3, " +
            "p.date, p.detailimage1, p.detailimage2, p.youtube, p.detailgif, " +
            "p.detailspec, p.soldout, p.sale, p.event, " +
            "i.imgid, i.filepath, i.thumbnail, " +
            "c.colorid, c.color, c.pcolor " +
            "FROM products p " +
            "LEFT JOIN images i ON p.prodid = i.prodid " +
            "LEFT JOIN colors c ON p.prodid = c.prodid " +
            "WHERE p.prodid = #{prodid} LIMIT 1")
    @Results(id = "productMap", value = {
        @Result(property = "prodid", column = "prodid"),
        @Result(property = "title", column = "title"),
        @Result(property = "proddesc", column = "proddesc"),
        @Result(property = "price", column = "price"),
        @Result(property = "type1", column = "type1"),
        @Result(property = "type2", column = "type2"),
        @Result(property = "type3", column = "type3"),
        @Result(property = "date", column = "date"),
        @Result(property = "detailimage1", column = "detailimage1"),
        @Result(property = "youtube", column = "youtube"),
        @Result(property = "detailgif", column = "detailgif"),
        @Result(property = "detailimage2", column = "detailimage2"),
        @Result(property = "detailspec", column = "detailspec"),
        @Result(property = "soldout", column = "soldout"),
        @Result(property = "sale", column = "sale"),
        @Result(property = "event", column = "event"),
        @Result(property = "images", column = "prodid", many = @Many(select = "selectImagesByProductId")),
        @Result(property = "colors", column = "prodid", many = @Many(select = "selectColorsByProductId"))
    })
    Product selectItem(Product input);

    /**
     * 다중행 조회를 위한 메서드 정의
     * @param input - 조회할 상품 정보에 대한 모델 객체
     * @return 조회된 데이터 리스트
     */
    @Select("<script>" +
        "SELECT p.prodid, p.title, p.proddesc, p.price, p.type1, p.type2, p.type3, " +
        "p.date, p.detailimage1, p.detailimage2, p.youtube, p.detailgif, " +
        "p.detailspec, p.soldout, p.sale, p.event, " +
        "i.imgid, i.filepath, i.thumbnail, " +
        "c.colorid, c.color, c.pcolor " +
        "FROM products p " +
        "LEFT JOIN images i ON p.prodid = i.prodid " +
        "LEFT JOIN colors c ON p.prodid = c.prodid " +
        "<where>" +
        "<if test='title != null'>AND p.title LIKE concat('%', #{title}, '%')</if>" +
        "<if test='price != null'>AND p.price LIKE concat('%', #{price}, '%')</if>" +
        "<if test='type1 != null'>AND p.type1 LIKE concat('%', #{type1}, '%')</if>" +
        "<if test='type2 != null'>AND p.type2 LIKE concat('%', #{type2}, '%')</if>" +
        "<if test='type3 != null'>AND p.type3 LIKE concat('%', #{type3}, '%')</if>" +
        "<if test='date != null'>AND p.date LIKE concat('%', #{date}, '%')</if>" +
        "<if test='proddesc != null'>AND p.proddesc LIKE concat('%', #{proddesc}, '%')</if>" +
        "<if test='detailimage1 != null'>AND p.detailimage1 LIKE concat('%', #{detailimage1}, '%')</if>" +
        "<if test='youtube != null'>AND p.youtube LIKE concat('%', #{youtube}, '%')</if>" +
        "<if test='detailgif != null'>AND p.detailgif LIKE concat('%', #{detailgif}, '%')</if>" +
        "<if test='detailimage2 != null'>AND p.detailimage2 LIKE concat('%', #{detailimage2}, '%')</if>" +
        "<if test='detailspec != null'>AND p.detailspec LIKE concat('%', #{detailspec}, '%')</if>" +
        "<if test='soldout != null'>AND p.soldout LIKE concat('%', #{soldout}, '%')</if>" +
        "<if test='sale != null'>AND p.sale LIKE concat('%', #{sale}, '%')</if>" +
        "<if test='event != null'>AND p.event LIKE concat('%', #{event}, '%')</if>" +
        "</where>" +
        "ORDER BY p.prodid DESC " +
        "<if test='listCount > 0'>LIMIT #{offset}, #{listCount}</if>" +
        "</script>")
        @ResultMap("productMap")
        List<Product> selectList(Product input);

// type1에 따른 상품 목록 조회
@Select("SELECT p.prodid, p.title, p.proddesc, p.price, p.type1, p.type2, p.type3, " +
        "p.date, p.detailimage1, p.detailimage2, p.youtube, p.detailgif, " +
        "p.detailspec, p.soldout, p.sale, p.event, " +
        "i.imgid, i.filepath, i.thumbnail, " +
        "c.colorid, c.color, c.pcolor " +
        "FROM products p " +
        "LEFT JOIN images i ON p.prodid = i.prodid " +
        "LEFT JOIN colors c ON p.prodid = c.prodid " +
        "WHERE p.type1 = #{type1} " +
        "ORDER BY p.prodid DESC ")
        @ResultMap("productMap")
        List<Product> selectListByType1(Product input);

// type2에 따른 상품 목록 조회
@Select("SELECT p.prodid, p.title, p.proddesc, p.price, p.type1, p.type2, p.type3, " +
        "p.date, p.detailimage1, p.detailimage2, p.youtube, p.detailgif, " +
        "p.detailspec, p.soldout, p.sale, p.event, " +
        "i.imgid, i.filepath, i.thumbnail, " +
        "c.colorid, c.color, c.pcolor " +
        "FROM products p " +
        "LEFT JOIN images i ON p.prodid = i.prodid " +
        "LEFT JOIN colors c ON p.prodid = c.prodid " +
        "WHERE p.type2 = #{type2} " +
        "ORDER BY p.prodid DESC ")
        @ResultMap("productMap")
        List<Product> selectListByType2(Product input);

// type3에 따른 상품 목록 조회
@Select("SELECT p.prodid, p.title, p.proddesc, p.price, p.type1, p.type2, p.type3, " +
        "p.date, p.detailimage1, p.detailimage2, p.youtube, p.detailgif, " +
        "p.detailspec, p.soldout, p.sale, p.event, " +
        "i.imgid, i.filepath, i.thumbnail, " +
        "c.colorid, c.color, c.pcolor " +
        "FROM products p " +
        "LEFT JOIN images i ON p.prodid = i.prodid " +
        "LEFT JOIN colors c ON p.prodid = c.prodid " +
        "WHERE p.type3 = #{type3} " +
        "ORDER BY p.prodid DESC ")
        @ResultMap("productMap")
        List<Product> selectListByType3(Product input);

// prodid에 따른 상품 조회
@Select("SELECT p.prodid, p.title, p.proddesc, p.price, p.type1, p.type2, p.type3, " +
        "p.date, p.detailimage1, p.detailimage2, p.youtube, p.detailgif, " +
        "p.detailspec, p.soldout, p.sale, p.event, " +
        "i.imgid, i.filepath, i.thumbnail, " +
        "c.colorid, c.color, c.pcolor " +
        "FROM products p " +
        "LEFT JOIN images i ON p.prodid = i.prodid " +
        "LEFT JOIN colors c ON p.prodid = c.prodid " +
        "WHERE p.prodid = #{prodid} " +
        "ORDER BY p.prodid DESC " +
        "LIMIT 1")
@ResultMap("productMap")
Product selectItemByProdid(int prodid);

}