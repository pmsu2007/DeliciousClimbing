package sangmyungdae.deliciousclimbing.dto;

import lombok.Builder;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;

public class Address {
    private long code;
    private String sido;
    private String sigungu;

    @Builder
    public Address(TbAddress entity) {
        this.code = entity.getCode();
        this.sido = entity.getSido();
        this.sigungu = entity.getSigungu();
    }
}
