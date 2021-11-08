package prgrms.al.back.product.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import prgrms.al.back.user.dto.SellerDto;

@Getter
public class ProductResponse {

    private Long productId;
    private String title;
    private String content;
    private Long price;
    private SellerDto seller;
    private String location;
    private LocalDateTime createdAt;

    @Builder
    public ProductResponse(Long productId, String title, String content, Long price,
        SellerDto seller, String location, LocalDateTime createdAt) {
        this.productId = productId;
        this.title = title;
        this.content = content;
        this.price = price;
        this.seller = seller;
        this.location = location;
        this.createdAt = createdAt;
    }
}
