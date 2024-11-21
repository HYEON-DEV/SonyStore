package kr.co.sonystore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sonystore.mappers.TestMapper;
import kr.co.sonystore.models.Product;
import kr.co.sonystore.services.TestService;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public Product getItem(Product input) throws Exception {
        Product output = testMapper.selectItem(input);

        if (output==null) {
            throw new Exception(" 조회된 데이터가 없습니다.");
        }

        return output;
    }
    
}
