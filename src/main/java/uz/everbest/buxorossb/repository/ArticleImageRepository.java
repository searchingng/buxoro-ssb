package uz.everbest.buxorossb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import uz.everbest.buxorossb.entity.ArticleImage;

import java.util.List;

public interface ArticleImageRepository extends JpaRepository<ArticleImage, Long> {

    List<ArticleImage> findByArticleId(Long articleId);

    @Transactional
    @Modifying
    void deleteByArticleId(Long articleId);

}
