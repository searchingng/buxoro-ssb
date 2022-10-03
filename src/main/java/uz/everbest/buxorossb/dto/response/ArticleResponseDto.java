package uz.everbest.buxorossb.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.everbest.buxorossb.dto.ArticleImageDto;
import uz.everbest.buxorossb.entity.enums.ArticleStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleResponseDto {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime publishedDate;

    private String publisherName;

    private Boolean isFeatured;

    private ArticleStatus status;

    private ArticleImageDto thumbnail;

}
