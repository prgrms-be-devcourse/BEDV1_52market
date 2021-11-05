package prgrms.al.back.letter.controller;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prgrms.al.back.letter.dto.*;
import prgrms.al.back.letter.service.LetterService;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/letters")
public class LetterController {

    private LetterService service;

    @PostMapping
    public ResponseEntity<LetterCreateResponse> createLetter(final @RequestBody @Valid LetterCreateRequest letterCreateRequest){
        return ResponseEntity.ok(service.createLetter(letterCreateRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<LetterFindResponse>> findLetter(@PathVariable Long id){
        return ResponseEntity.ok(service.findAllByUserId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LetterDeleteResponse> deleteLetter(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(service.deleteLetter(id));
    }
}
