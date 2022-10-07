package uz.everbest.buxorossb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.everbest.buxorossb.dto.ArticleDto;
import uz.everbest.buxorossb.dto.request.ArticleRequestDto;
import uz.everbest.buxorossb.dto.response.ArticleResponseDto;
import uz.everbest.buxorossb.entity.Article;

import java.util.List;

public interface ArticleService {

    ArticleDto save(ArticleRequestDto requestDto);

    boolean publish(Long articleId);

    boolean deActive(Long articleId);

    Page<ArticleResponseDto> getAll(Pageable pageable);

    List<ArticleDto> getMyArticles();

    ArticleResponseDto getOne(Long id);

    Article findById(Long id);

    void deleteById(Long articleId);
}
