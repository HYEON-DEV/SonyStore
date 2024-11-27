package kr.co.sonystore.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sonystore.mappers.BackgroundMapper;
import kr.co.sonystore.models.Background;
import kr.co.sonystore.services.BackgroundService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BackgroundServiceImpl implements BackgroundService {
    @Autowired
    private BackgroundMapper backgroundMapper;

    @Override
    public void addItem(Background input) throws Exception {
        try {
            backgroundMapper.insert(input);
        } catch (Exception e) {
            throw new Exception("배경 정보 입력에 실패했습니다.");
        }
    }

    @Override
    public void editItem(Background input) throws Exception {
        try {
            backgroundMapper.update(input);
        } catch (Exception e) {
            throw new Exception("배경 정보 수정에 실패했습니다.");
        }
    }

    @Override
    public Background deleteItem(Background input) throws Exception {
        try {
            backgroundMapper.delete(input);
        } catch (Exception e) {
            throw new Exception("배경 정보 삭제에 실패했습니다.");
        }
        return input;
    }

    @Override
    public List<Background> getItemList(Background input) throws Exception {
        List<Background> result = null;
        try {
            result = backgroundMapper.selectList(input);
        } catch (Exception e) {
            throw new Exception("배경 정보 조회에 실패했습니다.");
        }
                return result;
    }

   
}
