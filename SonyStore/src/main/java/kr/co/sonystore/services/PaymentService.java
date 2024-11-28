package kr.co.sonystore.services;

import java.util.List;

import kr.co.sonystore.models.Payment;


public interface PaymentService {

    /**
     * 결제내역에 결제정보를 추가한다
     * @param input - 입력할 결제정보에 대한 모델 객체
     * @return 추가된 데이터 수
     * @throws Exception - 데이터베이스 연결 문제, SQL 쿼리 오류, 입력 데이터 문제
     */
    public Payment addItem(Payment input) throws Exception;


    /**
     * 결제내역의 결제정보를 수정한다
     * @param input - 수정할 결제정보에 대한 모델 객체
     * @return 수정된 데이터 수
     * @throws Exception - 데이터베이스 연결 문제, SQL 쿼리 오류, 입력 데이터 문제
     */
    public Payment editItem(Payment input) throws Exception;


    /**
     * 결제내역을 단일 조회 한다
     * @param input - 조회할 결제정보 일련번호를 가진 모델 객체
     * @return 조회된 데이터
     * @throws Exception - 데이터베이스 연결 문제, SQL 쿼리 오류, 입력 데이터 문제
     */
    public Payment getItem(Payment input) throws Exception;


    /**
     * 결제내역 중 최근 5개 까지의 배송지를 조회한다
     * @param input - 조회할 배송지에 대한 결제내역 모델 객체
     * @return 조회된 데이터
     * @throws Exception - 데이터베이스 연결 문제, SQL 쿼리 오류, 입력 데이터 문제
     */
    public List<Payment> getDlvList(Payment input) throws Exception;


    /**
     * 결제내역을 삭제한다
     * @param input - 삭제할 결제내역에 대한 모델 객체
     * @return  삭제된 데이터 수
     * @throws Exception - 데이터베이스 연결 문제, SQL 쿼리 오류, 입력 데이터 문제
     */
    public int deleteItem(Payment input) throws Exception;

}
