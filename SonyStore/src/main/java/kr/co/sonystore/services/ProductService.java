package kr.co.sonystore.services;

import java.util.List;

import kr.co.sonystore.models.Product;

public interface ProductService {
    /**
     * 상품 정보를 입력한다
     * @param input - 입력할 상품 정보에 대한 모델 객체
     * @return 입력된 데이터 수
     */
    public int addItem(Product input) throws Exception;

    /**
     * 상품 정보를 업데이트한다
     * @param input - 업데이트할 상품 정보에 대한 모델 객체
     * @return 업데이트된 데이터 수
     */
    public int updateItem(Product input) throws Exception;

    /**
     * 상품 정보를 삭제한다
     * @param input - 삭제할 상품 정보에 대한 모델 객체
     * @return 삭제된 데이터 수
     */
    public int deleteItem(Product input) throws Exception;

    /**
     * 상품 정보를 조회한다
     * @param input - 조회할 상품 정보에 대한 모델 객체
     * @return 조회된 데이터 수
     */
    public Product getItem(Product input) throws Exception;

    /**
     * 상품 정보를 조회한다
     * @param input - 조회할 상품 정보에 대한 모델 객체
     * @return 조회된 데이터 수
     */

    public List<Product> getItemList(Product input) throws Exception;

    public List<Product> getItemListByType1(String type) throws Exception;
}
