package sangmyungdae.deliciousclimbing.dto.recommend;

import lombok.Builder;
import lombok.Data;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.domain.enums.Difficulty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class RecommendMountain {

    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    private String detailRegion;


    @Builder
    public RecommendMountain(TbFamousMountain entity,String detailRegion){
        this.id = entity.getId();
        this.name = entity.getName();
        this.difficulty = entity.getDifficulty();
        this.detailRegion = detailRegion;
    }

    public static List<RecommendMountain> toDtoList(List<TbFamousMountain> entities){
        return entities.stream().map(entity -> RecommendMountain.builder().entity(entity).build()).collect(Collectors.toList());
    }

}
