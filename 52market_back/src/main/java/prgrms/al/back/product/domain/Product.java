package prgrms.al.back.product.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prgrms.al.back.user.User;

@Entity(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String title;

    @Lob
    private String content;
    private Long price;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User createdBy;

    @Embedded
    private Location location;

    @Builder
    public Product(String title, String content, Long price, Location location) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.location = location;
        this.createdAt = LocalDateTime.now();
    }

    public void setOwner(User user) {
        createdBy = user;
        user.getProducts().add(this);
    }
}
