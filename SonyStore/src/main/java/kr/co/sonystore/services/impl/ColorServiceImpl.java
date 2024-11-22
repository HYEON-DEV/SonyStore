package kr.co.sonystore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import kr.co.sonystore.mappers.ColorMapper;

import kr.co.sonystore.models.Color;
import kr.co.sonystore.services.ColorService;

public class ColorServiceImpl implements ColorService {
    @Autowired
    private ColorMapper colorMapper;

    @Override
    public Color addItem(Color input) throws Exception {
        int result = colorMapper.insert(input);
        if (result == 0) {
            throw new Exception("색상 정보 입력에 실패했습니다.");
        }
        return input;
    }

    @Override
    public Color editItem(Color input) throws Exception {
        int result = colorMapper.update(input);
        if (result == 0) {
            throw new Exception("색상 정보 수정에 실패했습니다.");
        }
        return input;
    }

    @Override
    public Color deleteItem(Color input) throws Exception {
        int result = colorMapper.delete(input);
        if (result == 0) {
            throw new Exception("색상 정보 삭제에 실패했습니다.");
        }
        return input;
    }
    
}
