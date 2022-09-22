package uz.everbest.buxorossb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.everbest.buxorossb.entity.enums.ArticleStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime publishedDate;

    private Long userId;

    private Boolean isFeatured;

    private ArticleStatus status;

}
