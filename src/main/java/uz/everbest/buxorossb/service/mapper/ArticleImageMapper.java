package uz.everbest.buxorossb.service.mapper;

import org.mapstruct.Mapper;
import uz.everbest.buxorossb.dto.ArticleImageDto;
import uz.everbest.buxorossb.entity.ArticleImage;

@Mapper(componentModel = "spring")
public interface ArticleImageMapper extends EntityMapper<ArticleImageDto, ArticleImage> {
}
