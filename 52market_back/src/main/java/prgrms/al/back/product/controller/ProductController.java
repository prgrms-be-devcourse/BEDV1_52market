package prgrms.al.back.product.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.location.service.LocationService;
import prgrms.al.back.product.dto.ProductSearchResponse;
import prgrms.al.back.product.service.ProductService;
import prgrms.al.back.product.dto.ProductRequest;
import prgrms.al.back.user.dto.UserDto;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<HttpStatus> createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



    @GetMapping
    public ResponseEntity<List<ProductSearchResponse>> products(@RequestParam("location") String locationName) {

        Location location = locationService.findByName(locationName)
            .orElseThrow(() -> new RuntimeException("해당 이름을 가진 지역이 없습니다."));

        List<ProductSearchResponse> productSearchResponse = productService.findProductsByLocation(
            location);

        return new ResponseEntity<>(productSearchResponse, HttpStatus.OK);
    }

}
