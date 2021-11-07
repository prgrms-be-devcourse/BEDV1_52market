package prgrms.al.back.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import prgrms.al.back.product.service.ImageUploadService;

@RequiredArgsConstructor
@RestController
public class ImageUploadController {
    private final ImageUploadService imageUploadService;

    @PostMapping("/api/v1/upload")
    public String uploadImage(@RequestPart MultipartFile file){
        return imageUploadService.uploadImage(file);
    }
}
