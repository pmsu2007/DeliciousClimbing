package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "TB_MOUNTAIN")
public class TbMountain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "mountain")
    private List<TbMountainAddress> mountainAddresses = new ArrayList<>();
}
