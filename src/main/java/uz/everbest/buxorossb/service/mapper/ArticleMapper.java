package uz.everbest.buxorossb.service.mapper;

import org.mapstruct.Mapper;
import uz.everbest.buxorossb.dto.ArticleDto;
import uz.everbest.buxorossb.entity.Article;

@Mapper(componentModel = "spring")
public interface ArticleMapper extends EntityMapper<ArticleDto, Article>{

}
