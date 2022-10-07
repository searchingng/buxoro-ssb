package uz.everbest.buxorossb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.everbest.buxorossb.dto.ArticleDto;
import uz.everbest.buxorossb.dto.request.ArticleRequestDto;
import uz.everbest.buxorossb.dto.response.AlertResponseDto;
import uz.everbest.buxorossb.dto.response.ArticleResponseDto;
import uz.everbest.buxorossb.service.ArticleService;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleDto> saveNew(@RequestBody ArticleRequestDto requestDto){
        return ResponseEntity.ok(articleService.save(requestDto));
    }

    @PutMapping("/publish/{articleId}")
    public ResponseEntity<AlertResponseDto> publish(@PathVariable("articleId") Long articleId){
        return ResponseEntity.ok(
                new AlertResponseDto(articleService.publish(articleId))
        );
    }

    @PutMapping("/deactive/{articleId}")
    public ResponseEntity<AlertResponseDto> deActive(@PathVariable("articleId") Long articleId){
        return ResponseEntity.ok(
                new AlertResponseDto(articleService.deActive(articleId))
        );
    }

    @GetMapping
    public ResponseEntity<Page<ArticleResponseDto>> getAll(Pageable pageable){
        return ResponseEntity.ok(articleService.getAll(pageable));
    }

    @GetMapping("/my-articles")
    public ResponseEntity<List<ArticleDto>> getMyArticles(){
        return ResponseEntity.ok(articleService.getMyArticles());
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleResponseDto> getOne(@PathVariable("articleId") Long articleId){
        return ResponseEntity.ok(articleService.getOne(articleId));
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<AlertResponseDto> delete(@PathVariable("articleId") Long articleId){
        articleService.deleteById(articleId);
        return ResponseEntity.ok(new AlertResponseDto(true));
    }


}
