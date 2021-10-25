package prgrms.al.back.user;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prgrms.al.back.product.domain.Location;
import prgrms.al.back.product.domain.Product;

@Entity(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(name = "user_id")
    private Long id;

    private String nickName;

    @OneToMany(mappedBy = "createdBy")
    private List<Product> products = new ArrayList<>();

    @Embedded
    private Location location;

    public User(String nickName) {
        this.nickName = nickName;
    }
}
