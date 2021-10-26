package prgrms.al.back.product.dto;


import lombok.Builder;
import lombok.Data;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.user.User;

@Builder
@Data
public class ProductRequest {

    private String title;
    private String content;
    private Long price;
    private String nickName;


    public Product toEntity(User user) {
        Product product = Product.builder()
            .title(title)
            .content(content)
            .price(price)
            .location(user.getLocation())
            .build();
        product.setOwner(user);
        return product;
    }
}
