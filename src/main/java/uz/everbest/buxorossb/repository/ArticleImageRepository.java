package uz.everbest.buxorossb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.everbest.buxorossb.entity.ArticleImage;

import java.util.List;

public interface ArticleImageRepository extends JpaRepository<ArticleImage, Long> {

    List<ArticleImage> findByArticleId(Long articleId);

    void deleteByArticleId(Long articleId);

}
