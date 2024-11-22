package kr.co.sonystore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import kr.co.sonystore.mappers.ImageMapper;
import kr.co.sonystore.models.Image;
import kr.co.sonystore.services.ImageService;

public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageMapper imageMapper;

    public void addItem(Image input) throws Exception {
        try {
            imageMapper.insert(input);
        } catch (Exception e) {
            throw new Exception("이미지 정보 입력에 실패했습니다.");
        }
    }

    public void editItem(Image input) throws Exception {
        try {
            imageMapper.update(input);
        } catch (Exception e) {
            throw new Exception("이미지 정보 수정에 실패했습니다.");
        }
    }

    public Image deleteItem(Image input) throws Exception {
        try {
            imageMapper.delete(input);
        } catch (Exception e) {
            throw new Exception("이미지 정보 삭제에 실패했습니다.");
        }
        return input;
    }
}
