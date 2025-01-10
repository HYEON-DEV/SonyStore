package kr.co.sonystore.controllers.restfulapis;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.sonystore.helpers.FileHelper;
import kr.co.sonystore.helpers.RestHelper;
import kr.co.sonystore.models.Background;
import kr.co.sonystore.models.Product;
import kr.co.sonystore.services.BackgroundService;
import kr.co.sonystore.services.ProductService;

@RestController
@Tag(name="Product API", description="제품 관련 API")
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @Autowired
    private BackgroundService backgroundService;

    @Autowired
    private FileHelper fileHelper;

    @Autowired
    private RestHelper restHelper;

    @GetMapping("/api/products")
    @Operation(summary="제품 목록 조회", description="모든 제품 목록을 조회한다.")
    @ApiResponses(value={
        @ApiResponse(responseCode="200", description="제품 목록 조회 성공"),
        @ApiResponse(responseCode="500", description="제품 목록 조회 실패")
    })
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

    @GetMapping("/api/backgrounds")
    @Operation(summary="제품 배경화면 목록 조회", description="모든 제품 배경화면 목록을 조회한다.")
    @ApiResponses(value={
        @ApiResponse(responseCode="200", description="제품 배경화면 목록 조회 성공"),
        @ApiResponse(responseCode="500", description="제품 배경화면 목록 조회 실패")
    })
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

    @GetMapping("/api/products/{type}")
    @Operation(summary="타입별 제품 목록 조회", description="타입별로 제품 목록을 조회한다.")
    @ApiResponses(value={
        @ApiResponse(responseCode="200", description="타입별 제품 목록 조회 성공"),
        @ApiResponse(responseCode="500", description="타입별 제품 목록 조회 실패")
    })
    public Map<String, Object> getProductListByType(@PathVariable("type") String type) throws Exception {
        List<Product> output;
        try {
            Product input = new Product();
            input.setType1(type);
            output = productService.getItemListByType1(input);
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

    @GetMapping("/api/products/{type}/{type2}")
    @Operation(summary="타입2별 제품 목록 조회", description="타입2별로 제품 목록을 조회한다.")
    @ApiResponses(value={
        @ApiResponse(responseCode="200", description="타입2별 제품 목록 조회 성공"),
        @ApiResponse(responseCode="500", description="타입2별 제품 목록 조회 실패")
    })
    public Map<String, Object> getProductListByType2(@PathVariable("type") String type, @PathVariable("type2") String type2) throws Exception {
        List<Product> output;
        try {
            Product input = new Product();
            input.setType1(type);
            input.setType2(type2);
            output = productService.getItemListByType2(input);
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

    @GetMapping("/api/products/{type}/{type2}/{type3}")
    @Operation(summary="타입3별 제품 목록 조회", description="타입3별로 제품 목록을 조회한다.")
    @ApiResponses(value={
        @ApiResponse(responseCode="200", description="타입3별 제품 목록 조회 성공"),
        @ApiResponse(responseCode="500", description="타입3별 제품 목록 조회 실패")
    })
    public Map<String, Object> getProductListByType3(@PathVariable("type") String type, @PathVariable("type2") String type2, @PathVariable("type3") String type3) throws Exception {
        List<Product> output;
        try {
            Product input = new Product();
            input.setType1(type);
            input.setType2(type2);
            input.setType3(type3);
            output = productService.getItemListByType3(input);
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

    @GetMapping("/api/product-view/{prodid}")
    @Operation(summary="제품 상세 조회", description="제품 ID로 제품 상세 정보를 조회한다.")
    @ApiResponses(value={
        @ApiResponse(responseCode="200", description="제품 상세 조회 성공"),
        @ApiResponse(responseCode="500", description="제품 상세 조회 실패")
    })
    public Map<String, Object> getProductView(@PathVariable("prodid") int prodid) throws Exception {
        Product output;
        try {
            output = productService.getItemByProdId(prodid);
            if (output != null) {
                if (output.getImages() != null) {
                    output.getImages().forEach(image -> {
                        image.setFilepath(fileHelper.getUrl(image.getFilepath()));
                    });
                }
                if (output.getDetailimage1() != null) {
                    output.setDetailimage1(fileHelper.getUrl(output.getDetailimage1()));
                }
                if (output.getDetailgif() != null) {
                    output.setDetailgif(fileHelper.getUrl(output.getDetailgif()));
                }
                if (output.getDetailimage2() != null) {
                    output.setDetailimage2(fileHelper.getUrl(output.getDetailimage2()));
                }
                if (output.getDetailspec() != null) {
                    output.setDetailspec(fileHelper.getUrl(output.getDetailspec()));
                }
            }
        } catch (Exception e) {
            return restHelper.serverError(e);
        }
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("product", output);

        return restHelper.sendJson(data);
    }
}
