package prgrms.al.back.letter.service;

import javassist.NotFoundException;
import prgrms.al.back.letter.dto.*;

import java.util.List;

public interface LetterService {
    public LetterCreateResponse createLetter(LetterCreateRequest letterCreateRequest);
    public LetterDeleteResponse deleteLetter(Long id) throws NotFoundException;
    public List<LetterFindResponse> findAllByUserId(Long id);
}
