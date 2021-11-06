package prgrms.al.back.letter.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.user.domain.User;

import javax.persistence.*;

@Getter
@Entity(name = "letter")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "letter_id")
    private Long id;

    @Column(name = "body", updatable = false, length = 500)
    private String body;

    @OneToOne
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sender", referencedColumnName = "user_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver", referencedColumnName = "user_id")
    private User receiver;

    @Builder
    public Letter(String body, Product product, User sender, User receiver) {
        Assert.hasText(body, "Body is empty");
        Assert.notNull(product, "Product is empty");
        Assert.notNull(sender, "Sender is empty");
        Assert.notNull(receiver, "Receiver is empty");

        this.body = body;
        this.product = product;
        this.sender = sender;
        this.receiver = receiver;
    }
}
