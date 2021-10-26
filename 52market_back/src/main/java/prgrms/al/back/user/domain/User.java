package prgrms.al.back.user.domain;

import lombok.*;
import prgrms.al.back.attention.domain.Attention;
import prgrms.al.back.product.domain.Product;

import javax.persistence.*;
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

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "nick_name", unique = true)
    private String nickName;

    @Column(name = "password")
    private String password;

    @Column(name = "location")
    private Location location;

    @Column(name = "manner_temperature")
    private double mannerTemperature;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany
    private List<Attention> attentions;

    @OneToMany
    private List<Product> products;

    public void updateInfo(String name, String nickName, String password, Location location) {
        this.name = (name != null) ? name : this.name;
        this.nickName = (nickName != null) ? nickName : this.nickName;
        this.password = (password != null) ? password : this.password;
        this.location = (location != null) ? location : this.location;
    }
}


