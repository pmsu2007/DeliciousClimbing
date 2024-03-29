package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "TB_ADDRESS")
public class TbAddress {

    @Id
    private Long code; // 행정 코드

    @Column(nullable = false)
    private String sido;

    @Column(nullable = false)
    private String sigungu;

    @OneToMany(mappedBy = "address")
    private List<TbMountainAddress> mountainAddresses = new ArrayList<>();

    @OneToMany(mappedBy = "address")
    private List<TbFamousMountainAddress> famousMountainAddresses = new ArrayList<>();
    @Builder
    public TbAddress(Long code, String sido, String sigungu) {
        this.code = code;
        this.sido = sido;
        this.sigungu = sigungu;
    }
}
