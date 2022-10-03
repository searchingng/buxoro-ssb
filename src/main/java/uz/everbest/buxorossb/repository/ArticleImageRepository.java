package uz.everbest.buxorossb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import uz.everbest.buxorossb.entity.ArticleImage;
import uz.everbest.buxorossb.entity.enums.ImageType;

import java.util.List;
import java.util.Optional;

public interface ArticleImageRepository extends JpaRepository<ArticleImage, Long> {

    List<ArticleImage> findByArticleId(Long articleId);

    boolean existsByArticleIdAndType(Long articleId, ImageType type);

    ArticleImage findByArticleIdAndType(Long articleId, ImageType type);

    Optional<ArticleImage> findByPath(String path);

    @Transactional
    @Modifying
    void deleteByPath(String path);

    @Transactional
    @Modifying
    void deleteByArticleId(Long articleId);

}
