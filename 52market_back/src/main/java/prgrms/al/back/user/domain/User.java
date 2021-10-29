package prgrms.al.back.user.domain;

import lombok.*;
import prgrms.al.back.attention.domain.Attention;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.product.domain.Product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank
    @Pattern(regexp = "([A-Za-z0-9]){4,20}")
    @Column(name = "name", updatable = false, length = 50)
    private String name;

    @NotBlank
    @Pattern(regexp = "^(?!.*\\.\\.)(?!.*\\.$)[^\\W][\\w.]{4,20}")
    @Column(name = "nick_name", unique = true, length = 15)
    private String nickName;

    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}")
    @Column(name = "password", length = 20)
    private String password;

    @Column(name = "manner_temperature")
    private double mannerTemperature;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany
    private List<Attention> attentions;

    @OneToMany
    private List<Product> products;

    @ManyToOne
    private Location location;

    public void updateInfo(String name, String nickName, String password, Location location) {
        this.name = (name != null) ? name : this.name;
        this.nickName = (nickName != null) ? nickName : this.nickName;
        this.password = (password != null) ? password : this.password;
        this.location = (location != null) ? location : this.location;
    }

    public void updateLocationInfo(Location location){
        this.location = (location != null) ? location : this.location;
    }
}


