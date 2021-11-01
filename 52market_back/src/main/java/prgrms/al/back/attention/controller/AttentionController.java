package prgrms.al.back.attention.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prgrms.al.back.attention.dto.AttentionSaveRequestDto;
import prgrms.al.back.attention.service.AttentionService;
import prgrms.al.back.product.dto.ProductRequest;
import java.util.List;

@RestController
@RequestMapping("/api/attention")
@RequiredArgsConstructor
public class AttentionController {
    private AttentionService attentionService;

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody AttentionSaveRequestDto attentionSaveRequestDto) {
        return ResponseEntity.ok(attentionService.save(attentionSaveRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long attentionId) {
        return ResponseEntity.ok(attentionService.delete(attentionId));
    }

//    @GetMapping("/user/{id}")
//    public ResponseEntity<List<ProductRequest>>
}
