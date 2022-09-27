package uz.everbest.buxorossb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleImageDto {

    private Long id;

    private String path;

    private Boolean isThumb;

}
