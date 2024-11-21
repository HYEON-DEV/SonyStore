package kr.co.sonystore.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

import kr.co.sonystore.models.Image;


@Mapper
public interface ImageMapper {

    @Insert("INSERT INTO images (filepath, thumbnail, prodid, colorid) VALUES (#{filepath}, #{thumbnail}, #{prodid}, #{colorid})")
    @Options(useGeneratedKeys = true, keyProperty = "imgid", keyColumn = "imgid")
    void insert(Image input);

    @Update("UPDATE images SET filepath = #{filepath}, thumbnail = #{thumbnail}, prodid = #{prodid}, colorid = #{colorid} WHERE imgid = #{imgid}")
    void update(Image input);

    @Delete("DELETE FROM images WHERE imgid = #{imgid}")
    void delete(Image input);

    // 이미지를 삭제하기 전에 상품에 소속된 이미지 데이터를 삭제
    @Delete("DELETE FROM images WHERE prodid = #{prodid}")
    void deleteByProdid(Image input);

    // 이미지를 삭제하기 전에 색상에 소속된 이미지 데이터를 삭제
    @Delete("DELETE FROM images WHERE colorid = #{colorid}")
    void deleteByColorid(Image input);
}