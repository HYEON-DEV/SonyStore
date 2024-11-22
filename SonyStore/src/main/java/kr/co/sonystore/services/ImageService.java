package kr.co.sonystore.services;

import org.springframework.stereotype.Service;

import kr.co.sonystore.models.Image;

@Service
public interface ImageService {
    public void addItem(Image input) throws Exception;

    public void editItem(Image input) throws Exception;

    public Image deleteItem(Image input) throws Exception;
}
