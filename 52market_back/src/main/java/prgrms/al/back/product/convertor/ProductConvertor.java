package prgrms.al.back.product.convertor;

import org.springframework.stereotype.Component;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.product.dto.ProductRequest;
import prgrms.al.back.product.dto.ProductSearchResponse;
import prgrms.al.back.product.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConvertor {
    public ProductSearchResponse toDto(Product product){
        return ProductSearchResponse.builder()
                .title(product.getTitle())
                .price(product.getPrice())
                .location(product.getLocation())
                .totalAttention(product.getTotalAttention())
                .build();
    }

    public Product of(ProductSearchResponse productSearchResponse){
        return Product.builder()
                .title(productSearchResponse.getTitle())
                .price(productSearchResponse.getPrice())
                .location(productSearchResponse.getLocation())
                .totalAttention(productSearchResponse.getTotalAttention())
                .build();
    }

    public List<ProductSearchResponse> toListDto(List<Product> productList){
        List<ProductSearchResponse> productSearchResponseList = new ArrayList<>();
        for(Product product : productList){
            productSearchResponseList.add(ProductSearchResponse.builder()
                    .title(product.getTitle())
                    .price(product.getPrice())
                    .location(product.getLocation())
                    .totalAttention(product.getTotalAttention())
                    .build());
        }
        return productSearchResponseList;
    }
}
