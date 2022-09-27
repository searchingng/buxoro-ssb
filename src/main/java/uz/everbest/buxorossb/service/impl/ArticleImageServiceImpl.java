package uz.everbest.buxorossb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import uz.everbest.buxorossb.dto.ArticleImageDto;
import uz.everbest.buxorossb.entity.ArticleImage;
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
        image.setIsThumb(false);
        articleImageRepository.save(image);
        return articleImageMapper.toDto(image);
    }

    @Override
    public List<ArticleImageDto> reUploadBulk(Long articleId, List<MultipartFile> multiparts) {
        deleteByArticleId(articleId);
        List<ArticleImageDto> images = multiparts.stream()
                .map(multipart -> upload(articleId, multipart))
                .collect(Collectors.toList());

        String thumbPath = uploadService.thumbnail(images.get(0).getPath());
        ArticleImage thumb = new ArticleImage();
        thumb.setArticleId(articleId);
        thumb.setPath(thumbPath);
        thumb.setIsThumb(true);
        articleImageRepository.save(thumb);

        images.add(0, articleImageMapper.toDto(thumb));

        return images;
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
    public void deleteById(Long imageId) {
        ArticleImage image = findById(imageId);
        uploadService.delete(image.getPath());
        articleImageRepository.deleteById(imageId);
    }

    @Override
    public void deleteByArticleId(Long articleId) {
        List<ArticleImageDto> images = findByArticleId(articleId);
        images.forEach(old -> uploadService.delete(old.getPath()));
        articleImageRepository.deleteByArticleId(articleId);
    }
}
