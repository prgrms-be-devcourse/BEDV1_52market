package prgrms.al.back.product.domain;

import javax.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Location {

    private String city;
}
