package uz.everbest.buxorossb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uz.everbest.buxorossb.dto.ArticleDto;
import uz.everbest.buxorossb.dto.request.ArticleRequestDto;
import uz.everbest.buxorossb.dto.response.ArticleResponseDto;
import uz.everbest.buxorossb.entity.Article;
import uz.everbest.buxorossb.entity.User;
import uz.everbest.buxorossb.entity.enums.ArticleStatus;
import uz.everbest.buxorossb.repository.ArticleRepository;
import uz.everbest.buxorossb.service.ArticleImageService;
import uz.everbest.buxorossb.service.ArticleService;
import uz.everbest.buxorossb.service.mapper.ArticleMapper;
import uz.everbest.buxorossb.service.mapper.ArticleRequestMapper;
import uz.everbest.buxorossb.service.mapper.ArticleResponseMapper;
import uz.everbest.buxorossb.util.UserUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleImageService articleImageService;
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final ArticleRequestMapper articleRequestMapper;
    private final ArticleResponseMapper articleResponseMapper;

    @Override
    public ArticleDto save(ArticleRequestDto requestDto) {
        Article article = articleRequestMapper.toEntity(requestDto);

        if (requestDto.getId() != null && articleRepository.existsById(requestDto.getId())) {
            Article old = findById(requestDto.getId());
            checkOwner(old.getUserId());
        }

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

        checkOwner(article.getUserId());

        if (status.equals(ArticleStatus.PUBLISHED))
            article.setPublishedDate(LocalDateTime.now());

        articleRepository.save(article);
        return true;
    }

    @Override
    public Page<ArticleResponseDto> getAll(Pageable pageable) {
        return articleRepository
                .findAllByStatusOrderByPublishedDateDesc(ArticleStatus.PUBLISHED, pageable)
                .map(articleResponseMapper::toDto)
                .map(article -> {
                    article.setThumbnail(articleImageService.findThumbByArticleId(article.getId()));
                    article.setImages(articleImageService.findByArticleId(article.getId()));
                    return article;
                });
    }

    @Override
    public List<ArticleDto> getMyArticles() {
        User user = UserUtil.currentUser();
        return findByUserId(user.getId());
    }

    @Override
    public ArticleResponseDto getOne(Long id) {
        ArticleResponseDto dto = articleResponseMapper.toDto(findById(id));
        dto.setThumbnail(articleImageService.findThumbByArticleId(id));
        dto.setImages(articleImageService.findByArticleId(id));
        return dto;
    }

    @Override
    public Article findById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Article Not Found with ID: " + id
        ));
    }

    @Override
    public void deleteById(Long articleId) {
        articleImageService.deleteByArticleId(articleId);
        articleRepository.deleteById(articleId);
    }

    private void checkOwner(Long userId){
        if (!UserUtil.currentUser().getId().equals(userId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "you are not owner of Article");
    }

    private List<ArticleDto> findByUserId(Long userId){
        return articleRepository.findByUserId(userId)
                .stream().map(articleMapper::toDto)
                .collect(Collectors.toList());
    }

}
