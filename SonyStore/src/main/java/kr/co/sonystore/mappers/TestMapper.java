package kr.co.sonystore.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import kr.co.sonystore.models.Product;

@Mapper
public interface TestMapper {

    @Select ( "SELECT prodid, title, detailimage1 FROM products WHERE prodid=#{prodid}" )
    @Results(id = "productMap", value = {
        @Result(property = "prodid", column = "prodid"),
        @Result(property = "title", column = "title"),
        @Result(property = "detailimage1", column = "detailimage1")
    })
    Product selectItem(Product input);
}
