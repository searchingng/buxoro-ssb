package uz.everbest.buxorossb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.everbest.buxorossb.dto.ArticleImageDto;
import uz.everbest.buxorossb.dto.response.AlertResponseDto;
import uz.everbest.buxorossb.service.ArticleImageService;
import uz.everbest.buxorossb.service.UploadService;

@RestController
@RequestMapping("/api/articles/images")
@RequiredArgsConstructor
public class ArticleImageController {

    private final ArticleImageService articleImageService;
    private final UploadService uploadService;

    @PostMapping(value = "/{articleId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArticleImageDto> upload(@PathVariable("articleId") Long articleId,
                                                  @RequestParam("file") MultipartFile multipartFile){
        return ResponseEntity.ok(articleImageService.upload(articleId, multipartFile));
    }

    @PostMapping(value = "/upload-thumb/{articleId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArticleImageDto> uploadThumb(@PathVariable("articleId") Long articleId,
                                                  @RequestParam("file") MultipartFile multipartFile){
        return ResponseEntity.ok(articleImageService.uploadThumb(articleId, multipartFile));
    }

    @GetMapping(produces = {"image/png", "image/jpeg", "image/gif"})
    public ResponseEntity<byte[]> open(@RequestParam("path") String path){
        return ResponseEntity.ok()
                .header(HttpHeaders.CACHE_CONTROL, "max-age=864000, public")
                .body(uploadService.getResource(path));
    }

    @GetMapping(value = "/download", produces = {"image/png", "image/jpeg", "image/gif"})
    public ResponseEntity<byte[]> download(@RequestParam("path") String path){
        String fileName = path.substring(path.lastIndexOf("/") + 1);
        return ResponseEntity.ok()
                .header(HttpHeaders.CACHE_CONTROL, "max-age=864000, public")
                .header(HttpHeaders.CONTENT_TYPE, "image/png", "image/jpeg", "image/gif")
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(uploadService.getResource(path));
    }

    @DeleteMapping
    public ResponseEntity<AlertResponseDto> deleteByPath(@RequestParam("path") String path){
        articleImageService.deleteByPath(path);
        return ResponseEntity.ok(new AlertResponseDto(true));
    }

}
