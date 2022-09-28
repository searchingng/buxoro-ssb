package uz.everbest.buxorossb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.everbest.buxorossb.entity.Article;
import uz.everbest.buxorossb.entity.enums.ArticleStatus;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findAllByStatusOrderByPublishedDateDesc(ArticleStatus status, Pageable pageable);

    List<Article> findByUserId(Long userId);

}
