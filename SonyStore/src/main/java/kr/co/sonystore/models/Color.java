package kr.co.sonystore.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Color {
    private int colorid;
    private String color;
    private int prodid;

    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;
}
