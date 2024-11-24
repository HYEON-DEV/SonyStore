package kr.co.sonystore.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Image {
    private int imgid;
    private String filepath;
    private String thumbnail;
    private int prodid;
    private Integer colorid;

    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;
}
