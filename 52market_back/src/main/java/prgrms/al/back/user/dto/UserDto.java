package prgrms.al.back.user.dto;

import lombok.Builder;
import lombok.Getter;
import prgrms.al.back.attention.domain.Attention;
import prgrms.al.back.user.domain.Location;
import prgrms.al.back.product.domain.Product;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class UserDto {

    private Long userId;

    private String name;

    private String nickName;

    private String password;

    private Location location;

    private double mannerTemperature;

    private LocalDateTime createdAt;

    private List<Attention> attentions;

    private List<Product> products;
}
