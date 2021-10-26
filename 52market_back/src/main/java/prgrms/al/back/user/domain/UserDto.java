package prgrms.al.back.user.domain;

import lombok.Builder;
import lombok.Getter;
import prgrms.al.back.attention.Attention;
import prgrms.al.back.product.Product;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class UserDto {

    private Long userId;

    private String name;

    private String nickName;

    private String password;

    private String location;

    private double mannerTemperature;

    private LocalDateTime createdAt;

    private List<Attention> attentionList;

    private List<Product> productList;
}
