package kr.co.sonystore.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kr.co.sonystore.models.Product;
import kr.co.sonystore.services.ProductService;
import kr.co.sonystore.mappers.ProductMapper;

public class ProductServiceImpl implements ProductService {

    @Autowired ProductMapper productMapper;
    
    @Override
    public int addItem(Product input) throws Exception {
        int rows = 0;
        if (rows == 0) {
            throw new Exception("상품 정보 입력에 실패했습니다.");
        }
        return rows;
    }

    @Override
    public int updateItem(Product input) throws Exception {
        int rows = 0;
        if (rows == 0) {
            throw new Exception("상품 정보 수정에 실패했습니다.");
        }
        return rows;
    }

    @Override
    public int deleteItem(Product input) throws Exception {
        int rows = 0;
        if (rows == 0) {
            throw new Exception("상품 정보 삭제에 실패했습니다.");
        }
        return rows;
    }

    @Override
    public Product getItem(Product input) throws Exception {
        Product result = productMapper.selectItem(input);

        if(result == null) {
            throw new Exception("상품 정보 조회에 실패했습니다.");
        }
        return result;
    }

    @Override
    public List<Product> getItemList(Product input) throws Exception {
        List<Product> result = productMapper.selectList(input);

        if(result == null) {
            throw new Exception("상품 정보 조회에 실패했습니다.");
        }
        return result;
    }
    
    
}
