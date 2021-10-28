package prgrms.al.back.product.dto;

import lombok.Getter;

@Getter
public class LocationResponse {

    private String name;

    public LocationResponse(String name) {
        this.name = name;
    }
}
