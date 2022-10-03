package uz.everbest.buxorossb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.everbest.buxorossb.entity.ArticleImage;
import uz.everbest.buxorossb.entity.enums.ImageType;

import java.util.List;
import java.util.Optional;

public interface ArticleImageRepository extends JpaRepository<ArticleImage, Long> {

    @Query("SELECT i FROM ArticleImage i WHERE i.articleId = ?1 AND i.type = 'SIMPLE'")
    List<ArticleImage> findSimpleByArticleId(Long articleId);

    @Query("SELECT i FROM ArticleImage i WHERE i.articleId = ?1 AND i.type = 'THUMB'")
    Optional<ArticleImage> findThumbByArticleId(Long articleId);

    boolean existsByArticleIdAndType(Long articleId, ImageType type);

    Optional<ArticleImage> findByPath(String path);

    @Transactional
    @Modifying
    void deleteByPath(String path);

    @Transactional
    @Modifying
    void deleteByArticleIdAndType(Long articleId, ImageType type);

}
