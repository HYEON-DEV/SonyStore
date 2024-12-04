package kr.co.sonystore.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import kr.co.sonystore.models.Color;
import kr.co.sonystore.models.Image;
import kr.co.sonystore.models.Product;

@Mapper
public interface SearchMapper {
 
    /**
     * 검색 결과의 수를 조회하는 메서드
     * 목록 조회와 동일한 검색 조건을 적용해야 한다.
     * @param input - 조회 조건을 담고 있는 객체
     * @return  조회 결과 수
     */
    @Select("<script>" +
            "select count(*) " +
            "from products " +
            "<where>" +
            "<if test='title != null'>title like concat('%', #{title}, '%')</if>" +
            "<if test='proddesc != null'>or proddesc like concat('%', #{proddesc}, '%')</if> " +
            "<if test='type1 != null'>or type1 like concat('%', #{type1}, '%')</if> " +
            "<if test='type2 != null'>or type2 like concat('%', #{type2}, '%')</if> " +
            "<if test='type3 != null'>or type3 like concat('%', #{type3}, '%')</if> " +
            "</where>" +
            "</script>")
    public int selectCount(Product input);

    @Select("SELECT * FROM images WHERE prodid = #{prodid}")
    List<Image> selectImagesByProductId(int prodid);

    @Select("SELECT * FROM colors WHERE prodid = #{prodid}")
    @Results({
        @Result(property = "colorid", column = "colorid"),
        @Result(property = "color", column = "color"),
        @Result(property = "pcolor", column = "pcolor")
    })
    List<Color> selectColorsByProductId(int prodid);


    @Select("<script>" + 
            "select " +
            "p.prodid, p.title, p.proddesc, p.price, p.type1, " +
            "p.type2, p.type3, p.date, p.soldout, p.sale, " +
            "p.event, i.imgid, i.filepath, i.thumbnail, c.colorid, " +
            "c.color, c.pcolor " +
            "from products p " +
            "left join images i on p.prodid = i.prodid " +
            "left join colors c on p.prodid = c.prodid" +
            "<where>" +
            "<if test='keyword != null'>keyword like concat('%', #{keyword}, '%')</if>" +
            "and i.thumbnail = 'Y' and c.pcolor = 'Y'" +
            "</where>" +
            "order by p.prodid desc" +
            "</script>")
    @Results(id = "productMap", value = {
        @Result(property = "prodid", column = "prodid"),
        @Result(property = "title", column = "title"),
        @Result(property = "proddesc", column = "proddesc"),
        @Result(property = "price", column = "price"),
        @Result(property = "type1", column = "type1"),
        @Result(property = "type2", column = "type2"),
        @Result(property = "type3", column = "type3"),
        @Result(property = "date", column = "date"),
        @Result(property = "soldout", column = "soldout"),
        @Result(property = "sale", column = "sale"),
        @Result(property = "event", column = "event"),
        @Result(property = "images", column = "prodid", many = @Many(select = "selectImagesByProductId")),
        @Result(property = "colors", column = "prodid", many = @Many(select = "selectColorsByProductId"))
    })
    List<Product> selectList(Product input);
}
