package prgrms.al.back.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.product.dto.ProductRequest;
import prgrms.al.back.product.repository.ProductRepository;
import prgrms.al.back.user.User;
import prgrms.al.back.user.UserRepository;

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
}
