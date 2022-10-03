package uz.everbest.buxorossb.service;

import org.springframework.web.multipart.MultipartFile;
import uz.everbest.buxorossb.dto.ArticleImageDto;
import uz.everbest.buxorossb.entity.ArticleImage;

import java.util.List;

public interface ArticleImageService {

    ArticleImageDto upload(Long articleId, MultipartFile multipart);

    ArticleImageDto uploadThumb(Long articleId, MultipartFile multipart);

    ArticleImageDto createThumb(Long articleId, String path);

    List<ArticleImageDto> findByArticleId(Long articleId);

    ArticleImageDto findThumbByArticleId(Long articleId);

    ArticleImage findById(Long imageId);

    ArticleImage findByPath(String path);

    void deleteByPath(String path);

}
