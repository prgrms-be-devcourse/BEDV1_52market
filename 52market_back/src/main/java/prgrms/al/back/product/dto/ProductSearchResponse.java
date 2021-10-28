package prgrms.al.back.product.dto;

import lombok.Builder;
import lombok.Getter;
import prgrms.al.back.location.domain.Location;

@Getter
@Builder
public class ProductSearchResponse {

    // title, price, location, attention
    private String title;
    private Long price;
    private LocationResponse location;
    private int totalAttention;
}
