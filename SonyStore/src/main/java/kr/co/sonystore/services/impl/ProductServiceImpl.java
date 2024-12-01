package kr.co.sonystore.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sonystore.models.Image;
import kr.co.sonystore.models.Product;
import kr.co.sonystore.services.ProductService;
import kr.co.sonystore.mappers.ColorMapper;
import kr.co.sonystore.mappers.ImageMapper;
import kr.co.sonystore.mappers.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired ProductMapper productMapper;
    @Autowired ImageMapper imageMapper;
    @Autowired ColorMapper colorMapper;
    
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

        for (int i=0; i<result.size(); i++) {
            Product temp = result.get(i);

            Image image = new Image();
            image.setProdid(temp.getProdid());
            
            // imageMapper에서 prodId로 where절을 걸어서 이미지 목록을 가져온다.
            temp.setImages(imageMapper.selectImagesByProductId(temp));
            // colorMapper에서 prodId로 where절을 걸어서 색상 목록을 가져온다.
            temp.setColors(colorMapper.selectColorsByProductId(temp));
        }

        return result;
    }

    @Override
    public List<Product> getItemListByType1(Product input) throws Exception {
        List<Product> result = productMapper.selectListByType1(input);

        if (result == null) {
            throw new Exception("상품 정보 조회에 실패했습니다.");
        }

        // 중복된 제품 제거
        result = result.stream().distinct().collect(Collectors.toList());

        for (Product temp : result) {
            Image image = new Image();
            image.setProdid(temp.getProdid());

            temp.setImages(imageMapper.selectImagesByProductId(temp));
            temp.setColors(colorMapper.selectColorsByProductId(temp));
        }

        return result;
    }

    @Override
    public List<Product> getItemListByType2(Product input) throws Exception {
        List<Product> result = productMapper.selectListByType2(input);

        if (result == null) {
            throw new Exception("상품 정보 조회에 실패했습니다.");
        }

        // 중복된 제품 제거
        result = result.stream().distinct().collect(Collectors.toList());

        for (int i = 0; i < result.size(); i++) {
            Product temp = result.get(i);

            Image image = new Image();
            image.setProdid(temp.getProdid());

            temp.setImages(imageMapper.selectImagesByProductId(temp));
            temp.setColors(colorMapper.selectColorsByProductId(temp));
        }

        return result;
    }

    @Override
    public List<Product> getItemListByType3(Product input) throws Exception {
        List<Product> result = productMapper.selectListByType3(input);

        if (result == null) {
            throw new Exception("상품 정보 조회에 실패했습니다.");
        }

        // 중복된 제품 제거
        result = result.stream().distinct().collect(Collectors.toList());

        for (int i = 0; i < result.size(); i++) {
            Product temp = result.get(i);

            Image image = new Image();
            image.setProdid(temp.getProdid());

            temp.setImages(imageMapper.selectImagesByProductId(temp));
            temp.setColors(colorMapper.selectColorsByProductId(temp));
        }

        return result;
    }

    @Override
    public Product getItemByProdId(int prodid) throws Exception {
        Product result = productMapper.selectItemByProdid(prodid);

        if (result == null) {
            throw new Exception("상품 정보 조회에 실패했습니다.");
        }

        Image image = new Image();
        image.setProdid(result.getProdid());

        result.setImages(imageMapper.selectImagesByProductId(result));
        result.setColors(colorMapper.selectColorsByProductId(result));

        return result;
    }
}

