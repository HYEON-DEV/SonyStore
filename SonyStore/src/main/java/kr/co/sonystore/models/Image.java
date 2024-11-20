package kr.co.sonystore.models;

import lombok.Data;

@Data
public class Image {
    private int imgid;
    private String filepath;
    private String thumbnail;
    private int prodid;
    private int colorid;
}
