package kr.co.sonystore.controllers.restfulapis;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.sonystore.helpers.FileHelper;
import kr.co.sonystore.helpers.RestHelper;
import kr.co.sonystore.models.Background;
import kr.co.sonystore.models.Image;
import kr.co.sonystore.models.Product;
import kr.co.sonystore.services.BackgroundService;
import kr.co.sonystore.services.ProductService;

@RestController
// @RequestMapping("/api/products")
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @Autowired
    private BackgroundService backgroundService;

    @Autowired
    private FileHelper fileHelper;

    @Autowired
    private RestHelper restHelper;

    // // 제품 목록 조회
    // @GetMapping("/api/products")
    // public List<Product> getProductList() throws Exception {
    //     return productService.getItemList(null);
    // }

    //제품 목록 조회
    @GetMapping("/api/products")
    public Map<String, Object> getProductList() throws Exception {
        Product input = new Product();
        List<Product> output;
        try {
            output = productService.getItemList(input);
            if (output != null) {
                output.forEach(product -> {
                    if (product.getImages() != null) {
                        product.getImages().forEach(image -> {
                            image.setFilepath(fileHelper.getUrl(image.getFilepath()));
                        });
                    }
                });
            }
        } catch (Exception e) {
            return restHelper.serverError(e);
        }
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("list", output);

        return restHelper.sendJson(data);
    }


    // 제품 배경화면 목록 조회
    @GetMapping("/api/backgrounds")
    public Map<String, Object> getBackgroundList() throws Exception {
        Background input = new Background();
        List<Background> output;
        try {
            output = backgroundService.getItemList(input);
            if (output != null) {
                output.forEach(background -> {
                    background.setFilepath(fileHelper.getUrl(background.getFilepath()));
                });
            }
        } catch (Exception e) {
            return restHelper.serverError(e);
        }
        Map<String, Object> data2 = new LinkedHashMap<>();
        data2.put("list", output);

        return restHelper.sendJson(data2);
    }


    // 타입별 제품 목록 조회
@GetMapping("/api/products/{type}")
public Map<String, Object> getProductListByType(@PathVariable String type) throws Exception {
    List<Product> output;
    try {
        output = productService.getItemListByType1(type);
        if (output != null) {
            output.forEach(product -> {
                if (product.getImages() != null) {
                    product.getImages().forEach(image -> {
                        image.setFilepath(fileHelper.getUrl(image.getFilepath()));
                    });
                }
            });
        }
    } catch (Exception e) {
        return restHelper.serverError(e);
    }
    Map<String, Object> data = new LinkedHashMap<>();
    data.put("list", output);

    return restHelper.sendJson(data);
}

    // // 제품 상세 조회
    // @GetMapping("/{id}")
    // public Product getProduct(@PathVariable int id) throws Exception {
    //     Product input = new Product();
    //     input.setProdid(id);
    //     return productService.getItem(input);
    // }

    // // 제품 등록
    // @PostMapping
    // public int addProduct(@RequestBody Product product) throws Exception {
    //     return productService.addItem(product);
    // }

    // // 제품 수정
    // @PutMapping("/{id}")
    // public int updateProduct(@PathVariable int id, @RequestBody Product product) throws Exception {
    //     product.setProdid(id);
    //     return productService.updateItem(product);
    // }

    // // 제품 삭제
    // @DeleteMapping("/{id}")
    // public int deleteProduct(@PathVariable int id) throws Exception {
    //     Product input = new Product();
    //     input.setProdid(id);
    //     return productService.deleteItem(input);
    // }

    
}
