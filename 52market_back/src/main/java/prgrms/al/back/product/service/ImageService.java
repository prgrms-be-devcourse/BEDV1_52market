package prgrms.al.back.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prgrms.al.back.product.domain.Image;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.product.dto.ProductRequest;
import prgrms.al.back.product.repository.ImageRepository;
import prgrms.al.back.product.repository.ProductRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {

    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;
    public void saveImage(Long productId, String url){
        Product product = productRepository.findById(productId).get();
        Image image = Image.builder()
                .product(product)
                .url(url)
                .build();
        imageRepository.save(image);
    }
    public List<Image> findImagesByProductId(Long productId){
        return imageRepository.findImagesByProductId(productId);
    }
}
