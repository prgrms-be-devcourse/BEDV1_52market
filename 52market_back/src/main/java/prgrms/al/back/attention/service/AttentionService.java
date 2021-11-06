package prgrms.al.back.attention.service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prgrms.al.back.attention.domain.Attention;
import prgrms.al.back.attention.dto.AttentionSaveRequestDto;
import prgrms.al.back.attention.repository.AttentionRepository;
import prgrms.al.back.product.controller.ProductController;
import prgrms.al.back.product.convertor.ProductConvertor;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.product.dto.ProductSearchResponse;
import prgrms.al.back.product.service.ProductService;
import prgrms.al.back.user.convertor.UserConvertor;
import prgrms.al.back.user.domain.User;
import prgrms.al.back.user.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AttentionService {
    private final AttentionRepository attentionRepository;
    private final UserService userService;
    private final ProductService productService;
    private final UserConvertor userConvertor;
    private final ProductConvertor productConvertor;

    public Long save(AttentionSaveRequestDto attentionSaveRequestDto) throws NotFoundException {
        User user = userConvertor.of(userService.findById(attentionSaveRequestDto.getUserId()));
        Product product = productConvertor.of(productService.findById(attentionSaveRequestDto.getProductId()));
        productService.attentionPP(product); //attention ++

        return attentionRepository.save(attentionSaveRequestDto.toEntity(user, product)).getId();
    }

    public Long delete(Long attentionId) {
        Attention attention = attentionRepository.findById(attentionId)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 관심이 없습니다"));
        attentionRepository.delete(attention);
        return attentionId;
    }

    public List<ProductSearchResponse> getAttentionProducts(Long userId) {
        return productConvertor.toListDto(attentionRepository.findAttentionProducts(userId));
    }

}
