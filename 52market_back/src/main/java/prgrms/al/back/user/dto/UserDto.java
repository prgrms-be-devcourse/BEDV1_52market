package prgrms.al.back.user.dto;

import lombok.Builder;
import lombok.Getter;
import prgrms.al.back.attention.domain.Attention;
import prgrms.al.back.product.domain.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class UserDto {

    private Long userId;

    @NotBlank
    @Pattern(regexp = "([A-Za-z0-9]){4,20}")
    private String name;

    @NotBlank
    @Pattern(regexp = "^(?!.*\\.\\.)(?!.*\\.$)[^\\W][\\w.]{4,20}")
    private String nickName;

    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}")
    private String password;

    private String location;

    private double mannerTemperature;

    private LocalDateTime createdAt;

    private List<Attention> attentions;

    private List<Product> products;
}
