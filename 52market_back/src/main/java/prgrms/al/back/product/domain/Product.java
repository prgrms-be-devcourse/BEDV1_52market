package prgrms.al.back.product.domain;

import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.*;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.product.dto.ProductSearchResponse;
import prgrms.al.back.user.domain.User;

@Entity(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    private Location location;

    @Column(name = "total_attention")
    private int totalAttention;


    public Product(String title, String content, Long price, Location location) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.location = location;
    }

    @Builder
    public Product(String title, String content, Long price, Location location, int totalAttention) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.location = location;
        this.createdAt = LocalDateTime.now();
        this.totalAttention = totalAttention;
    }

    public void setOwner(User user) {
        createdBy = user;
        user.getProducts().add(this);
    }

    public int attentionPP() {
        totalAttention += 1;
        return totalAttention;
    }
}
