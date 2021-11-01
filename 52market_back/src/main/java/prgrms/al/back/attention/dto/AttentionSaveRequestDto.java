package prgrms.al.back.attention.dto;

import lombok.Builder;
import lombok.Getter;
import prgrms.al.back.attention.domain.Attention;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.user.domain.User;

@Getter
public class AttentionSaveRequestDto {
    private Long userId;
    private Long productId;

    @Builder
    public AttentionSaveRequestDto(Long userId,Long productId){
        this.userId = userId;
        this.productId = productId;
    }

    public Attention toEntity(User user, Product product){
        return Attention.builder()
                .user(user)
                .product(product)
                .build();
    }
}
