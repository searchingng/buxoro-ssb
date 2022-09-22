package uz.everbest.buxorossb.service;

import org.springframework.web.multipart.MultipartFile;
import uz.everbest.buxorossb.entity.ArticleImage;

import java.util.List;

public interface ArticleImageService {

    ArticleImage upload(Long articleId, MultipartFile multipart);

    List<ArticleImage> reUploadBulk(Long articleId, List<MultipartFile> multiparts);

    List<ArticleImage> findByArticleId(Long articleId);

    ArticleImage findById(Long imageId);

    void deleteById(Long imageId);

    void deleteByArticleId(Long articleId);
}
