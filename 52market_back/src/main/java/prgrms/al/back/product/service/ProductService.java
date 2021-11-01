package prgrms.al.back.product.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prgrms.al.back.product.convertor.ProductConvertor;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.product.dto.ProductRequest;
import prgrms.al.back.product.dto.ProductSearchResponse;
import prgrms.al.back.product.repository.ProductRepository;
import prgrms.al.back.user.domain.User;
import prgrms.al.back.user.dto.UserDto;
import prgrms.al.back.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductConvertor productConvertor;

    public void createProduct(ProductRequest productRequest) {

        String nickName = productRequest.getNickName();
        User user = userRepository.findByNickName(nickName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Product product = productRequest.toEntity(user);

        productRepository.save(product);
    }

    public List<ProductSearchResponse> findProducts(UserDto userDto) {
        return null;
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

    public int attentionPP(Product product){
        return product.attentionPP();
    }
}
