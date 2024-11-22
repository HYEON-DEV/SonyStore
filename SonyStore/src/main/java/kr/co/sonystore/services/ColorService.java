package kr.co.sonystore.services;

import org.springframework.stereotype.Service;

import kr.co.sonystore.models.Color;

@Service
public interface ColorService {
    public Color addItem(Color input) throws Exception;

    public Color editItem(Color input) throws Exception;

    public Color deleteItem(Color input) throws Exception;
    
    // public Color getItem(Color input) throws Exception;

    // public List<Color> getList(Color input) throws Exception;

    // public int getCount(Color input) throws Exception;
} 
