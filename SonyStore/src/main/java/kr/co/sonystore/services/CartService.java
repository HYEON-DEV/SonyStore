package kr.co.sonystore.services;

import java.util.List;

import kr.co.sonystore.models.Cart;

public interface CartService {

    /**
     * 장바구니에 상품이 존재하면 수량을 늘리고, 없으면 새로 추가한다
     * @param input - 저장할 정보를 담고있는 장바구니 객체
     * @return 저장된 장바구니 객체
     * @throws Exception
     */
    public Cart addOrEditItem(Cart input) throws Exception;


    /**
     * 장바구니 상품의 수량을 변경한다
     * @param input - 변경할 정보를 담고있는 장바구니 객체
     * @return 변경된 정보를 담고있는 장바구니 객체
     * @throws Exception
     */
    public Cart editItem(Cart input) throws Exception;


    /**
     * 장바구니 상품을 단일 삭제한다
     * @param input - 삭제할 장바구니 정보를 담고있는 객체
     * @return 삭제된 데이터 수
     * @throws Exception
     */
    public int deleteItem(Cart input) throws Exception;


    /**
     * 장바구니 상품을 다중 삭제한다
     * @param input - 삭제할 장바구니 정보를 담고있는 객체 리스트
     * @return 삭제된 데이터 수
     * @throws Exception
     */
    public int deleteList(List<Cart> input) throws Exception;


    /**
     * 장바구니를 조회한다
     * @param input - 조회할 장바구니 정보에 대한 모델 객체
     * @return 조회된 데이터가 저장된 객체
     * @throws Exception
     */
    public List<Cart> getList(Cart input) throws Exception;

    // public int getCount(Cart input) throws Exception;
}
