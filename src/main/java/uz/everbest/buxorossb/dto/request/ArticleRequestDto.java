package uz.everbest.buxorossb.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequestDto {

    private Long id;

    private String title;

    private String content;

    private Boolean isFeatured;

}
