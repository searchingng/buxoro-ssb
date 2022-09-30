package uz.everbest.buxorossb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import uz.everbest.buxorossb.dto.ArticleImageDto;
import uz.everbest.buxorossb.entity.ArticleImage;
import uz.everbest.buxorossb.entity.enums.ImageType;
import uz.everbest.buxorossb.repository.ArticleImageRepository;
import uz.everbest.buxorossb.service.ArticleImageService;
import uz.everbest.buxorossb.service.UploadService;
import uz.everbest.buxorossb.service.mapper.ArticleImageMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleImageServiceImpl implements ArticleImageService {

    private final ArticleImageRepository articleImageRepository;
    private final UploadService uploadService;
    private final ArticleImageMapper articleImageMapper;

    @Override
    public ArticleImageDto upload(Long articleId, MultipartFile multipart) {
        String path = uploadService.upload(multipart);
        ArticleImage image = new ArticleImage();
        image.setArticleId(articleId);
        image.setPath(path);

        if (!articleImageRepository.existsByArticleIdAndType(articleId, ImageType.THUMB)){
            createThumb(articleId, path);
            image.setType(ImageType.MAIN);
        } else {
            image.setType(ImageType.SIMPLE);
        }
        articleImageRepository.save(image);
        return articleImageMapper.toDto(image);
    }

    @Override
    public ArticleImageDto createThumb(Long articleId, String path) {
        String thumbPath = uploadService.thumbnail(path);
        ArticleImage thumb = new ArticleImage();
        thumb.setArticleId(articleId);
        thumb.setPath(thumbPath);
        thumb.setType(ImageType.THUMB);
        articleImageRepository.save(thumb);
        return articleImageMapper.toDto(thumb);
    }

    @Override
    public List<ArticleImageDto> findByArticleId(Long articleId) {
        return articleImageRepository.findByArticleId(articleId)
                .stream().map(articleImageMapper::toDto)
                .collect(Collectors.toList());
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
    public ArticleImage findByPath(String path) {
        return articleImageRepository.findByPath(path)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image Not Found"));
    }

    @Override
    public void deleteByPath(String path) {
        uploadService.delete(path);
        articleImageRepository.deleteByPath(path);
    }

    @Override
    public void deleteByArticleId(Long articleId) {
        List<ArticleImageDto> images = findByArticleId(articleId);
        images.forEach(old -> uploadService.delete(old.getPath()));
        articleImageRepository.deleteByArticleId(articleId);
    }
}
