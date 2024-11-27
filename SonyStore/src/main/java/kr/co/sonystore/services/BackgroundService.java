package kr.co.sonystore.services;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.sonystore.models.Background;

@Service
public interface BackgroundService {
    
    public void addItem(Background input) throws Exception;

    public void editItem(Background input) throws Exception;

    public Background deleteItem(Background input) throws Exception;

    public List<Background> getItemList(Background input) throws Exception;
}
