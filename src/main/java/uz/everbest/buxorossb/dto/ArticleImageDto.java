package uz.everbest.buxorossb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.everbest.buxorossb.entity.enums.ImageType;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleImageDto {

    private Long id;

    private String path;

    private ImageType type;

}
