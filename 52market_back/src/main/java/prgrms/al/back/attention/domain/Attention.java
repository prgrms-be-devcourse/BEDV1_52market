package prgrms.al.back.attention.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.user.domain.User;

import javax.persistence.*;

@Getter
@Entity(name = "attention")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attention_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @Builder
    public Attention(User user, Product product) {
        this.user = user;
        this.product = product;
    }
}
