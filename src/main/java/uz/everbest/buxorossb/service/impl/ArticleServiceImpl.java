package uz.everbest.buxorossb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uz.everbest.buxorossb.dto.ArticleDto;
import uz.everbest.buxorossb.dto.request.ArticleRequestDto;
import uz.everbest.buxorossb.entity.Article;
import uz.everbest.buxorossb.entity.enums.ArticleStatus;
import uz.everbest.buxorossb.repository.ArticleRepository;
import uz.everbest.buxorossb.service.ArticleService;
import uz.everbest.buxorossb.service.mapper.ArticleMapper;
import uz.everbest.buxorossb.service.mapper.ArticleRequestMapper;
import uz.everbest.buxorossb.util.UserUtil;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final ArticleRequestMapper articleRequestMapper;

    @Override
    public ArticleDto save(ArticleRequestDto requestDto) {
        Article article = articleRequestMapper.toEntity(requestDto);
        article.setStatus(ArticleStatus.DRAFT);
        article.setUserId(UserUtil.currentUser().getId());
        articleRepository.save(article);
        ArticleDto dto = articleMapper.toDto(article);
        return dto;
    }

    @Override
    public boolean publish(Long articleId) {
        return changeStatus(articleId, ArticleStatus.PUBLISHED);
    }

    @Override
    public boolean deActive(Long articleId) {
        return changeStatus(articleId, ArticleStatus.INACTIVE);
    }

    private boolean changeStatus(Long articleId, ArticleStatus status){
        Article article = findById(articleId);
        article.setStatus(status);

        if (status.equals(ArticleStatus.PUBLISHED))
            article.setPublishedDate(LocalDateTime.now());

        articleRepository.save(article);
        return true;
    }

    @Override
    public Page<ArticleDto> getAll(Pageable pageable) {
        return articleRepository.findAll(pageable).map(articleMapper::toDto);
    }

    @Override
    public ArticleDto getOne(Long id) {
        return articleMapper.toDto(findById(id));
    }

    @Override
    public Article findById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Article Not Found with ID: " + id
        ));
    }
}
