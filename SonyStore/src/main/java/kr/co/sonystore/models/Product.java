package kr.co.sonystore.models;

import java.util.List;
import java.util.Objects;

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
    private String keyword;

    private String pcolor;

    private List<Image> images;
    private List<Color> colors;

    @Getter
    @Setter
    private static int listCount = 0;

    @Getter
    @Setter
    private static int offset = 0;


    
    @Override
    public boolean equals(Object o) {
        // 동일한 객체인지 확인
        if (this == o) return true;
        // 객체가 null이거나 클래스가 다른 경우 false 반환
        if (o == null || getClass() != o.getClass()) return false;
        // 객체를 Product 타입으로 캐스팅
        Product product = (Product) o;
        // prodid 필드를 기준으로 두 객체가 동일한지 비교
        return prodid == product.prodid;
    }

    @Override
    public int hashCode() {
        // prodid 필드를 기반으로 해시 코드를 생성
        return Objects.hash(prodid);
    }
}
