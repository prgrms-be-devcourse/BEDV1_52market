package prgrms.al.back.letter.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LetterDeleteResponse {
    private String message;

    @Builder
    public LetterDeleteResponse(String message) {
        this.message = message;
    }
}
