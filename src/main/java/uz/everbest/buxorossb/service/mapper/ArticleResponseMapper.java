package uz.everbest.buxorossb.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.everbest.buxorossb.dto.response.ArticleResponseDto;
import uz.everbest.buxorossb.entity.Article;

@Mapper(componentModel = "spring")
public interface ArticleResponseMapper extends EntityMapper<ArticleResponseDto, Article>{

    @Override
    @Mapping(source = "user.name", target = "publisherName")
    ArticleResponseDto toDto(Article entity);
}
