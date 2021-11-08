package prgrms.al.back.user.dto;

import lombok.Getter;

@Getter
public class SellerDto {

    private String name;
    private String nickname;
    private String location;
    private double mannerTemperature;

    public SellerDto(String name, String nickname, String location, double mannerTemperature) {
        this.name = name;
        this.nickname = nickname;
        this.location = location;
        this.mannerTemperature = mannerTemperature;
    }
}
