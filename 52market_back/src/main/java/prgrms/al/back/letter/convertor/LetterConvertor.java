package prgrms.al.back.letter.convertor;

import org.springframework.stereotype.Component;
import prgrms.al.back.letter.domain.Letter;
import prgrms.al.back.letter.dto.LetterCreateRequest;
import prgrms.al.back.letter.dto.LetterCreateResponse;
import prgrms.al.back.letter.dto.LetterFindResponse;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.user.domain.User;

@Component
public class LetterConvertor {
    public Letter of(LetterCreateRequest letterCreateRequest, Product product, User sender, User receiver) {
        return Letter.builder()
                .body(letterCreateRequest.getBody())
                .product(product)
                .sender(sender)
                .receiver(receiver)
                .build();
    }

    public LetterCreateResponse toResponse(Letter letter) {
        return LetterCreateResponse.builder()
                .id(letter.getId())
                .body(letter.getBody())
                .senderId(letter.getSender().getId())
                .receiverId(letter.getReceiver().getId())
                .productId(letter.getProduct().getId())
                .build();
    }

    public LetterFindResponse toFindResponse(Letter letter){
        return LetterFindResponse.builder()
                .id(letter.getId())
                .body(letter.getBody())
                .senderId(letter.getSender().getId())
                .receiverId(letter.getReceiver().getId())
                .productId(letter.getProduct().getId())
                .build();
    }
}
