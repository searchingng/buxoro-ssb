package uz.everbest.buxorossb.service;

import org.springframework.web.multipart.MultipartFile;
import uz.everbest.buxorossb.dto.ArticleImageDto;
import uz.everbest.buxorossb.entity.ArticleImage;

import java.util.List;

public interface ArticleImageService {

    ArticleImageDto upload(Long articleId, MultipartFile multipart);

    List<ArticleImageDto> reUploadBulk(Long articleId, List<MultipartFile> multiparts);

    List<ArticleImageDto> findByArticleId(Long articleId);

    ArticleImage findById(Long imageId);

    void deleteById(Long imageId);

    void deleteByArticleId(Long articleId);
}
