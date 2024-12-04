package kr.co.sonystore.services;

import kr.co.sonystore.models.Product;

import java.util.List;

public interface SearchService {

    public int getCount(Product input) throws Exception;

    public List<Product> selectList(Product input) throws Exception;
}
