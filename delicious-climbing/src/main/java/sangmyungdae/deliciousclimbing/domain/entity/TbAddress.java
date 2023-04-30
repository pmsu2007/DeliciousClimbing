package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sangmyungdae.deliciousclimbing.domain.enums.Region;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "TB_ADDRESS")
public class TbAddress {

    @Id
    private Long code; // 행정 코드

    private String reason;

    private String season;

    @Enumerated(EnumType.STRING)
    private Region region;

    @OneToMany(mappedBy = "famousMountain")
    private List<TbFamousMountainAddress> famousMountainAdresses = new ArrayList<>();
}
