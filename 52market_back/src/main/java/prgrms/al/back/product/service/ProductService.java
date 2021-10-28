package prgrms.al.back.product.service;

import static java.util.stream.Collectors.*;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.product.dto.LocationResponse;
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

    public void createProduct(ProductRequest productRequest) {

        String nickName = productRequest.getNickName();
        User user = userRepository.findByNickName(nickName)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Product product = productRequest.toEntity(user);

        productRepository.save(product);
    }

    public List<ProductSearchResponse> findProductsByLocation(Location location) {
        List<Product> products = productRepository.findByLocation(location);
        return products.stream()
            .map(product -> ProductSearchResponse.builder()
                .title(product.getTitle())
                .price(product.getPrice())
                .location(new LocationResponse(product.getLocation().getName()))
                .totalAttention(product.getTotalAttention())
                .build())
            .collect(toList());

    }
}
