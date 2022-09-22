package uz.everbest.buxorossb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.everbest.buxorossb.dto.ArticleDto;
import uz.everbest.buxorossb.dto.request.ArticleRequestDto;
import uz.everbest.buxorossb.entity.Article;

public interface ArticleService {

    ArticleDto save(ArticleRequestDto requestDto);

    boolean publish(Long articleId);

    boolean deActive(Long articleId);

    Page<ArticleDto> getAll(Pageable pageable);

    ArticleDto getOne(Long id);

    Article findById(Long id);

}
