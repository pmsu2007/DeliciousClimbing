package sangmyungdae.deliciousclimbing.dto.mate;

import lombok.*;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MateFamousMountain {

    private Long id;
    private String mtName;

    @Builder
    public MateFamousMountain(TbFamousMountain entity) {
        this.id = entity.getId();
        this.mtName = entity.getName();
    }

    public static List<MateFamousMountain> toDtoList(List<TbFamousMountain> entities) {
        return entities.stream().map(entity -> MateFamousMountain.builder().entity(entity).build()).collect(Collectors.toList());
    }
}
