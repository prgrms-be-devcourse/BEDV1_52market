package prgrms.al.back.attention;

import lombok.Builder;
import lombok.Getter;
import prgrms.al.back.product.Product;
import prgrms.al.back.user.User;

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
        // userService를 통해 userId에 해당하는 user를 조회해서 넘겨준다.
        // productService를 통해 product에 해당하는 product를 조회해서 넘겨준다.
        return Attention.builder()
                .user(user)
                .product(product)
                .build();
    }
}
