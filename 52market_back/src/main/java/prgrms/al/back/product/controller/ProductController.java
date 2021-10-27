package prgrms.al.back.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prgrms.al.back.product.service.ProductService;
import prgrms.al.back.product.dto.ProductRequest;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<HttpStatus> createProduct(@RequestBody ProductRequest productRequest) {

        productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
