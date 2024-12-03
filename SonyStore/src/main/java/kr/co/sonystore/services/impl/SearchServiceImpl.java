package kr.co.sonystore.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sonystore.mappers.SearchMapper;
import kr.co.sonystore.models.Product;
import kr.co.sonystore.services.SearchService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchMapper searchMapper;

    @Override
    public int selectCount(Product input) throws Exception {
        int rows = 0;

        try {
            rows = searchMapper.selectCount(input);
            if (rows == 0) {
                throw new Exception("비밀번호 확인이 잘못되었거나 존재하지 않는 회원에 대한 요청입니다");
            }
        } catch (Exception e) {
            log.error("회원탈퇴에 실패했습니다.", e);
            throw e;
        }

        return rows;
    }

    @Override
    public List<Product> selectList(Product input) throws Exception {
        
        List<Product> output = null;

        try {
            output = searchMapper.selectList(input);
        } catch (Exception e) {
            throw new Exception("탈퇴 처리에 실패했습니다.");
        }

        return output;

    }
}
