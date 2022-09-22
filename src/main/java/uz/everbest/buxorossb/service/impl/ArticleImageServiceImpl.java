package uz.everbest.buxorossb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import uz.everbest.buxorossb.entity.ArticleImage;
import uz.everbest.buxorossb.repository.ArticleImageRepository;
import uz.everbest.buxorossb.service.ArticleImageService;
import uz.everbest.buxorossb.service.UploadService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleImageServiceImpl implements ArticleImageService {

    private final ArticleImageRepository articleImageRepository;
    private final UploadService uploadService;

    @Override
    public ArticleImage upload(Long articleId, MultipartFile multipart) {
        String path = uploadService.upload(multipart);
        ArticleImage image = new ArticleImage();
        image.setArticleId(articleId);
        image.setPath(path);
        articleImageRepository.save(image);
        return image;
    }

    @Override
    public List<ArticleImage> reUploadBulk(Long articleId, List<MultipartFile> multiparts) {
        return multiparts.stream()
                .map(multipart -> upload(articleId, multipart))
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleImage> findByArticleId(Long articleId) {
        return null;
    }

    @Override
    public ArticleImage findById(Long imageId) {
        return articleImageRepository.findById(imageId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Image Not Found with ID: " + imageId
                )
        );
    }

    @Override
    public void deleteById(Long imageId) {
        articleImageRepository.deleteById(imageId);
    }

    @Override
    public void deleteByArticleId(Long articleId) {
        articleImageRepository.deleteByArticleId(articleId);
    }
}