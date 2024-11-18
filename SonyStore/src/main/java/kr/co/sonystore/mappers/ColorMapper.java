package kr.co.sonystore.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import kr.co.sonystore.models.Color;


@Mapper
public interface ColorMapper {
    
    /**
     * 색상 정보를 입력한다
     * @param input - 입력할 색상 정보에 대한 모델 객체
     * @return 입력된 데이터 수
     */
    @Insert("INSERT INTO colors (color) VALUES ( #{color} )" )
    @Options(useGeneratedKeys = true, keyProperty = "colorid", keyColumn = "colorid")
    public int insert(Color input);
}
