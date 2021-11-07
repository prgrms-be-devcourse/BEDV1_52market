package prgrms.al.back.product.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity ( name = "image" )
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(name = "url", nullable = false, length = 300)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    private void setProduct(Product product){
        this.product = product;
        product.getPhotos().add(this);
    }

    @Builder
    public Image(Product product,String url){
        this.url = url;
        setProduct(product);
    }

}
