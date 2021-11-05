package prgrms.al.back.letter.service;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prgrms.al.back.letter.convertor.LetterConvertor;
import prgrms.al.back.letter.domain.Letter;
import prgrms.al.back.letter.dto.*;
import prgrms.al.back.letter.repository.LetterRepository;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.product.repository.ProductRepository;
import prgrms.al.back.user.domain.User;
import prgrms.al.back.user.exception.ErrorCode;
import prgrms.al.back.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LetterServiceImpl implements LetterService{

    LetterRepository repository;
    LetterConvertor convertor;
    ProductRepository productRepository;
    UserRepository userRepository;

    @Transactional
    @Override
    public LetterCreateResponse createLetter(LetterCreateRequest letterCreateRequest) {
        Product product = productRepository.findById(letterCreateRequest.getProductId()).get();
        User sender = userRepository.findById(letterCreateRequest.getSenderId()).get();
        User receiver = userRepository.findById(letterCreateRequest.getReceiverId()).get();
        Letter letter = convertor.of(letterCreateRequest, product, sender, receiver);
        repository.save(letter);
        LetterCreateResponse letterCreateResponse = convertor.toResponse(repository.findById(letter.getId()).get());
        return letterCreateResponse;
    }

    @Transactional
    @Override
    public LetterDeleteResponse deleteLetter(Long id) throws NotFoundException{
        if (!repository.existsById(id)){
            throw new NotFoundException(ErrorCode.NOT_FOUND_LETTER.toString());
        }

        repository.deleteById(id);
        return LetterDeleteResponse.builder()
                .message("success")
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public List<LetterFindResponse> findAllByUserId(Long id) {
        List<Letter> letters = repository.findAllBySenderAndReceiver(id);
        List<LetterFindResponse> letterFindResponses = new ArrayList<>();
        for (Letter letter : letters){
            letterFindResponses.add(convertor.toFindResponse(letter));
        }
        return letterFindResponses;
    }
}
