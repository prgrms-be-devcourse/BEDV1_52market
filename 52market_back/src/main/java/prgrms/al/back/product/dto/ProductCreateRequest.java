package prgrms.al.back.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.user.domain.User;
import prgrms.al.back.user.dto.SellerDto;

@Getter
public class ProductCreateRequest {

    @NotBlank
    @Length(min = 2, max = 100)
    private String title;

    @NotBlank
    @Length(min = 2, max = 500)
    private String content;

    @NotNull
    @Range(min = 100, max = 10_000_000)
    private long price;

    @JsonProperty("seller")
    @NotNull
    private SellerDto seller;

    private List<String> imageUrls;

    @NotBlank
    private String location;

    @Builder
    public ProductCreateRequest(String title, String content, long price,
        SellerDto seller, List<String> imageUrls, String location) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.seller = seller;
        this.imageUrls = imageUrls;
        this.location = location;
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
