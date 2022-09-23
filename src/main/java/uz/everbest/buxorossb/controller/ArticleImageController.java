package uz.everbest.buxorossb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.everbest.buxorossb.entity.ArticleImage;
import uz.everbest.buxorossb.service.ArticleImageService;

import java.util.List;

@RestController
@RequestMapping("/api/article/images")
@RequiredArgsConstructor
public class ArticleImageController {

    private final ArticleImageService articleImageService;

    @PostMapping(value = "/{articleId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArticleImage> upload(@PathVariable("articleId") Long articleId,
                                               @RequestParam("file") MultipartFile multipartFile){
        return ResponseEntity.ok(articleImageService.upload(articleId, multipartFile));
    }

    @PostMapping(value = "/bulk/{articleId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ArticleImage>> uploadBulk(@PathVariable("articleId") Long articleId,
                                                         @RequestParam("files") List<MultipartFile> multipartFiles){
        return ResponseEntity.ok(articleImageService.reUploadBulk(articleId, multipartFiles));
    }

}
