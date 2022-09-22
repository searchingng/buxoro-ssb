package uz.everbest.buxorossb.service.mapper;

import org.mapstruct.Mapper;
import uz.everbest.buxorossb.dto.request.ArticleRequestDto;
import uz.everbest.buxorossb.entity.Article;

@Mapper(componentModel = "spring")
public interface ArticleRequestMapper extends EntityMapper<ArticleRequestDto, Article>{

}
