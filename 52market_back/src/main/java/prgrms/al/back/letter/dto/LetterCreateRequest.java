package prgrms.al.back.letter.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class LetterCreateRequest {
    @NotBlank
    private String body;
    @NotNull
    private Long productId;
    @NotNull
    private Long senderId;
    @NotNull
    private Long receiverId;

    @Builder
    public LetterCreateRequest(String body, Long productId, Long senderId, Long receiverId) {
        this.body = body;
        this.productId = productId;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}
