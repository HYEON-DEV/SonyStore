package kr.co.sonystore.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import kr.co.sonystore.models.Image;


@Mapper
public interface ImageMapper {

    @Insert("INSERT INTO images (filepath, thumbnail, prodid, colorid) VALUES (#{filepath}, #{thumbnail}, #{prodid}, #{colorid})")
    
    void insertImage(Image image);

    @Update("UPDATE images SET filepath = #{filepath}, thumbnail = #{thumbnail}, prodid = #{prodid}, colorid = #{colorid} WHERE imgid = #{imgid}")
    void updateImage(Image image);
}