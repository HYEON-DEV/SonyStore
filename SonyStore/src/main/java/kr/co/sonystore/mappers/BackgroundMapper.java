package kr.co.sonystore.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.sonystore.models.Background;

@Mapper
public interface BackgroundMapper {
    @Insert("INSERT INTO backgrounds (type, filepath) VALUES (#{type}, #{filepath})")
    @Options(useGeneratedKeys = true, keyProperty = "bgid", keyColumn = "bgid")
    void insert(Background input);

    @Update("UPDATE backgrounds SET type = #{type}, filepath = #{filepath} WHERE bgid = #{bgid}")
    void update(Background input);

    @Delete("DELETE FROM backgrounds WHERE bgid = #{bgid}")
    public int delete(Background input);

    @Select("SELECT * FROM backgrounds")
    
    @Results(id = "backgroundMap", value = {
        @Result(property = "bgid", column = "bgid", id = true),
        @Result(property = "type", column = "type"),
        @Result(property = "filepath", column = "filepath")
    })
    List<Background> selectList(Background input);
    
} 