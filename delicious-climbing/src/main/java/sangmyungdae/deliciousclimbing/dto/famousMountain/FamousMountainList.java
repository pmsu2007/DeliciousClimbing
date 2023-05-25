package sangmyungdae.deliciousclimbing.dto.famousMountain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;


import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FamousMountainList {

    private long id;
    private String name;
    private String imageUrl;

    @Builder
    public FamousMountainList(TbFamousMountain entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.imageUrl = entity.getImageUrl();
    }


    public static List<FamousMountainList> toDtoList(List<TbFamousMountain> entities) {
        return entities.stream().map(entity -> FamousMountainList.builder().entity(entity).build()).collect(Collectors.toList());
    }
}
