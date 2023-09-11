package sangmyungdae.deliciousclimbing.dto.address;

import lombok.*;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private Long code;
    private String sido;
    private String sigungu;

    private String sidoSigungu;

    @Builder
    public Address(TbAddress entity) {
        if (entity != null) {
            this.code = entity.getCode();
            this.sido = entity.getSido();
            this.sigungu = entity.getSigungu();
            this.sidoSigungu = entity.getSido() + " " + entity.getSigungu();
        } else {
            this.code = null;
            this.sido = "";
            this.sigungu = "";
            this.sidoSigungu = "";
        }
    }
}
