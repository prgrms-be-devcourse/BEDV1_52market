package prgrms.al.back.product;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "product")
public class Product {
    @Id
    private Long id;
}
