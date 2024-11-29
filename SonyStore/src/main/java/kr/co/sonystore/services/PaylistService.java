package kr.co.sonystore.services;

import java.util.List;

import kr.co.sonystore.models.Paylist;


public interface PaylistService {

    /**
     * 결제 상품 목록에 구매할 상품 정보를 추가한다
     * @param input - 입력할 결제 상품 정보에 대한 모델 객체
     * @return 추가된 데이터 수
     * @throws Exception - 데이터베이스 연결 문제, SQL 쿼리 오류, 입력 데이터 문제
     */
    public Paylist addItem(Paylist input) throws Exception;

    // public Paylist editItem(Paylist input) throws Exception;


    /**
     * 미결제 상품의 결제 목록을 삭제한다
     * @param input - 삭제할 결제 상품 정보에 대한 모델 객체
     * @return 삭제된 데이터 수
     * @throws Exception - 데이터베이스 연결 문제, SQL 쿼리 오류, 입력 데이터 문제
     */
    public int deleteByNoPayments(Paylist input) throws Exception;


    /**
     * 결제 상품을 단일 조회한다
     * @param input - 조회할 결제 상품 일련번호를 가진 모델 객체
     * @return 조회된 데이터
     * @throws Exception - 데이터베이스 연결 문제, SQL 쿼리 오류, 입력 데이터 문제
     */
    public Paylist getItem(Paylist input) throws Exception;


    /**
     * 결제 상품 목록을 조회한다 
     * @param input - 조회할 결제 상품 정보에 대한 모델 객체
     * @return 조회된 데이터
     * @throws Exception - 데이터베이스 연결 문제, SQL 쿼리 오류, 입력 데이터 문제
     */
    public List<Paylist> getList(Paylist input) throws Exception;

}
