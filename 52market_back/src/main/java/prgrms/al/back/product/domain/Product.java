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

import lombok.*;
import prgrms.al.back.product.dto.ProductSearchResponse;
import prgrms.al.back.user.domain.Location;
import prgrms.al.back.user.domain.User;

@Entity(name = "product")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue
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

    @Column(name = "total_attention")
    private int totalAttention;

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

    public int attentionPP(){
        totalAttention +=1;
        return totalAttention;
    }
}
