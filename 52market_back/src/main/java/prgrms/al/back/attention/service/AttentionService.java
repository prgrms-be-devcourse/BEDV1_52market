package prgrms.al.back.attention.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prgrms.al.back.attention.domain.Attention;
import prgrms.al.back.attention.dto.AttentionSaveRequestDto;
import prgrms.al.back.attention.repository.AttentionRepository;
import prgrms.al.back.product.domain.Product;

import java.util.List;

@Service
public class AttentionService {
    private final AttentionRepository attentionRepository;
//    private final UserService userService;
//    private final ProductService productService;

    @Autowired
    public AttentionService(AttentionRepository attentionRepository) {
        this.attentionRepository = attentionRepository;
    }

    @Transactional
    public Long save(AttentionSaveRequestDto attentionSaveRequestDto) {
        // product의 attentionCount ++; 로직 추가해야함

        //User user = userService.findById(attentionSaveRequestDto.getUserId());
        //Product product = productService.findById(attentionSaveRequestDto.getProductId());
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

    //@Transactional
    //public List<Product> getAttentionProducts(Long userId){
//        return attentionRepository.findAttentionProducts(userId);
//    }

}
