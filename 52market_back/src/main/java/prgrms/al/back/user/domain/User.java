package prgrms.al.back.user.domain;

import lombok.*;
import prgrms.al.back.attention.domain.Attention;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.product.domain.Product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "user")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    @Pattern(regexp = "([A-Za-z0-9]){4,20}")
    @Column(name = "name", updatable = false, length = 50)
    private String name;

    @NotBlank
    @Pattern(regexp = "^(?!.*\\.\\.)(?!.*\\.$)[^\\W][\\w.]{4,20}")
    @Column(name = "nickname", unique = true, length = 15)
    private String nickname;

    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}")
    @Column(name = "password", length = 20)
    private String password;

    @Column(name = "manner_temperature")
    private double mannerTemperature;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private List<Attention> attentions = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy")
    private List<Product> products = new ArrayList<>();

    @ManyToOne
    private Location location;

    @Builder
    public User(String name, String nickname, String password, Location location) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.location = location;
        this.mannerTemperature = 36.5;
        this.createdAt = LocalDateTime.now();
    }

    public void updateInfo(String name, String nickName, String password, Location location) {
        this.name = (name != null) ? name : this.name;
        this.nickname = (nickName != null) ? nickName : this.nickname;
        this.password = (password != null) ? password : this.password;
        this.location = (location != null) ? location : this.location;
    }

    public void updateLocationInfo(Location location) {
        this.location = (location != null) ? location : this.location;
    }
}


