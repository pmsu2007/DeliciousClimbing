package sangmyungdae.deliciousclimbing.dto.address;

import lombok.*;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;
@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private Long code;
    private String sido;
    private String sigungu;

    @Builder
    public Address(TbAddress entity) {
        this.code = entity.getCode();
        this.sido = entity.getSido();
        this.sigungu = entity.getSigungu();
    }
}
