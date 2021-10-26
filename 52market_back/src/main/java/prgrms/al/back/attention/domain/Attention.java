package prgrms.al.back.attention.domain;

import lombok.Builder;
import lombok.Getter;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.user.domain.User;

import javax.persistence.*;

@Getter
@Entity(name = "attention")
public class Attention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attention_id")
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    protected Attention() {
    }

    @Builder
    public Attention(User user, Product product) {
        this.user = user;
        this.product = product;
    }
}
