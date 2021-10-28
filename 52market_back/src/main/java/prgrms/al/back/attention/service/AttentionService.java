package prgrms.al.back.attention.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prgrms.al.back.attention.domain.Attention;
import prgrms.al.back.attention.dto.AttentionSaveRequestDto;
import prgrms.al.back.attention.repository.AttentionRepository;
import prgrms.al.back.product.controller.ProductController;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.product.service.ProductService;
import prgrms.al.back.user.convertor.UserConvertor;
import prgrms.al.back.user.domain.User;
import prgrms.al.back.user.service.UserService;

@Service
public class AttentionService {
    private final AttentionRepository attentionRepository;
    private final UserService userService;
    private final ProductService productService;
    private final UserConvertor userConvertor;

    @Autowired
    public AttentionService(AttentionRepository attentionRepository, UserService userService, ProductService productService, UserConvertor userConvertor, ProductController productController) {
        this.attentionRepository = attentionRepository;
        this.userService = userService;
        this.productService = productService;
        this.userConvertor = userConvertor;
    }

    @Transactional
    public Long save(AttentionSaveRequestDto attentionSaveRequestDto) {
        // product의 attentionCount ++; 로직 추가해야함

        User user = userConvertor.of(userService.findById(attentionSaveRequestDto.getUserId()));
        //Product product = pproductService.findById(attentionSaveRequestDto.getProductId());
        //return AttentionRepository.save(attentionSaveRequestDto.toEntity(user, product));
        return 0L;
    }

    @Transactional
    public Long delete(Long attentionId){
            Attention attention = attentionRepository.findById(attentionId)
                    .orElseThrow(()->
                            new IllegalArgumentException("해당 관심이 없습니다"));
            attentionRepository.delete(attention);
            return attentionId;
    }

//    @Transactional
//    public List<Product> getAttentionProducts(Long userId){
//        return attentionRepository.findAttentionProducts(userId);
//    }

}
