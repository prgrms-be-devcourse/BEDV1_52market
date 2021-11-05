package prgrms.al.back.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDeleteResponse {

    private String message;

    @Builder
    public UserDeleteResponse(String message) {
        this.message = message;
    }
}
