package prgrms.al.back.letter.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LetterCreateResponse {

    private Long id;
    private String body;
    private Long productId;
    private Long senderId;
    private Long receiverId;

    @Builder
    public LetterCreateResponse(Long id, String body, Long productId, Long senderId, Long receiverId) {
        this.id = id;
        this.body = body;
        this.productId = productId;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}
