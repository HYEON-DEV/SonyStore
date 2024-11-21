package kr.co.sonystore.models;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Product {
    private int prodid;
    private String title;
    private String proddesc;
    private int price;
    private String type1;
    private String type2;
    private String type3;
    private String date;
    private String detailimage1;
    private String youtube;
    private String detailgif;
    private String detailimage2;
    private String detailspec;
    private String soldout;
    private String sale;
    private String event;

    private List<Image> images;
    private List<Color> colors;

    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;
}
