package kr.co.sonystore.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

import kr.co.sonystore.models.Color;
import kr.co.sonystore.models.Product;


@Mapper
public interface ColorMapper {
    
    /**
     * 색상 정보를 입력한다
     * @param input - 입력할 색상 정보에 대한 모델 객체
     * @return 입력된 데이터 수
     */
    @Insert("INSERT INTO colors (color, prodid, pcolor) VALUES (#{color}, #{prodid}, #{pcolor})")
    @Options(useGeneratedKeys = true, keyProperty = "colorid", keyColumn = "colorid")
    public int insert(Color input);

    /**
     * 색상 정보를 수정한다
     * @param input - 수정할 색상 정보에 대한 모델 객체
     * @return 수정된 데이터 수
     */
    @Update("UPDATE colors SET color = #{color}, prodid = #{prodid}, pcolor = #{pcolor} WHERE colorid = #{colorid}")
    public int update(Color input);

    /**
     * 색상 정보를 삭제한다
     * @param colorid - 삭제할 색상 정보의 식별자
     * @return 삭제된 데이터 수
     */
    @Delete("DELETE FROM colors WHERE colorid = #{colorid}")
    public int delete(Color input);


    @Select("SELECT * FROM colors WHERE prodid = #{prodid}")
    @Results({
        @Result(property = "colorid", column = "colorid"),
        @Result(property = "color", column = "color"),
        @Result(property = "pcolor", column = "pcolor")
    })
    List<Color> selectColorsByProductId(Product temp);
    
}
