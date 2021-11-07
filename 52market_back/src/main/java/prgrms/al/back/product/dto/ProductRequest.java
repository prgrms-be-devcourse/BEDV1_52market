package prgrms.al.back.product.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prgrms.al.back.product.domain.Image;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.user.domain.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ProductRequest {

    private String title;
    private String content;
    private Long price;
    private String nickname;
    private List<String> urlList;

    public ProductRequest(String title, String content, Long price, String nickname, List<String> urlList) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.nickname = nickname;
        this.urlList = urlList;
    }

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
