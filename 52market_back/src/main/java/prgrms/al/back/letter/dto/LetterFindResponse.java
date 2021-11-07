package prgrms.al.back.letter.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LetterFindResponse {
    private Long id;

    private String body;

    private Long senderId;

    private Long receiverId;

    private Long productId;

    @Builder
    public LetterFindResponse(Long id, String body, Long senderId, Long receiverId, Long productId) {
        this.id = id;
        this.body = body;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.productId = productId;
    }
}
