package prgrms.al.back.product.dto;

import lombok.Builder;
import lombok.Getter;
import prgrms.al.back.user.domain.Location;

@Getter
@Builder
public class ProductSearchResponse {

    // title, price, location, attention
    private String title;
    private Long price;
    private Location location;
    private int totalAttention;
}
