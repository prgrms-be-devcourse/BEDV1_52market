package prgrms.al.back.product.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.product.convertor.ProductConvertor;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.product.dto.ProductRequest;
import prgrms.al.back.product.dto.ProductSearchResponse;
import prgrms.al.back.product.repository.ProductRepository;
import prgrms.al.back.user.domain.User;
import prgrms.al.back.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductConvertor productConvertor;
    private final ImageService imageService;

    public void createProduct(ProductRequest productRequest) {

        String nickName = productRequest.getNickname();
        User user = userRepository.findByNickname(nickName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Product product = productRequest.toEntity(user);
        productRepository.save(product);
        productRepository.flush();

        //  이미지 저장
        for(String url : productRequest.getUrlList()){
            imageService.saveImage(product,url);
        }
    }

    public ProductSearchResponse findById(Long productId) {
        Product product = productRepository.findById(productId).get();
        ProductSearchResponse productSearchResponse = ProductSearchResponse.builder()
                .title(product.getTitle())
                .price(product.getPrice())
                .location(product.getLocation())
                .totalAttention(product.getTotalAttention())
                .build();
        return productSearchResponse;
    }

    @Transactional(readOnly = true)
    public List<ProductSearchResponse> findByLocation(Location location) {
        List<Product> products = productRepository.findByLocation(location);
        return products.stream()
            .map(product -> ProductSearchResponse.builder()
                .title(product.getTitle())
                .price(product.getPrice())
                .location(product.getLocation())
                .totalAttention(product.getTotalAttention())
                .build())
            .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<ProductSearchResponse> findByTitleContaining(Location location, String keyword) {
        List<Product> products = productRepository.findByLocationAndTitleContaining(location, keyword);
        return products.stream()
            .map(product -> ProductSearchResponse.builder()
                .title(product.getTitle())
                .price(product.getPrice())
                .location(product.getLocation())
                .totalAttention(product.getTotalAttention())
                .build())
            .collect(toList());
    }


    public int attentionPP(Product product){
        return product.attentionPP();
    }
}
