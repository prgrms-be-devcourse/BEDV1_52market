package prgrms.al.back.product.controller;

import static prgrms.al.back.product.controller.ProductController.*;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.location.service.LocationService;
import prgrms.al.back.product.dto.ProductCreateRequest;
import prgrms.al.back.product.dto.ProductSearchResponse;
import prgrms.al.back.product.service.ProductService;

@RestController
@RequestMapping(PRODUCT_API_URI)
@RequiredArgsConstructor
public class ProductController {

    public static final String PRODUCT_API_URI = "/api/products";

    private final ProductService productService;
    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody @Valid ProductCreateRequest productCreateRequest) {
        Long productId = productService.createProduct(productCreateRequest);
        return ResponseEntity.created(URI.create(PRODUCT_API_URI + "/" + productId)).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductSearchResponse>> products(@RequestParam("location") String locationName) {

        Location location = locationService.findByName(locationName)
            .orElseThrow(() -> new RuntimeException("해당 이름을 가진 지역이 없습니다."));

        List<ProductSearchResponse> productSearchResponse = productService.findByLocation(
            location);

        return new ResponseEntity<>(productSearchResponse, HttpStatus.OK);
    }

    @GetMapping("{keyword}")
    public ResponseEntity<List<ProductSearchResponse>> searchProductsByKeyword(@RequestParam("location") String locationName, @PathVariable String keyword) {

        Location location = locationService.findByName(locationName)
            .orElseThrow(() -> new RuntimeException("해당 이름을 가진 지역이 없습니다."));

        List<ProductSearchResponse> productSearchResponse = productService.findByTitleContaining(
            location, keyword);

        return new ResponseEntity<>(productSearchResponse, HttpStatus.OK);
    }


}
