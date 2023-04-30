package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "TB_MATE")
public class TbMate extends TbDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private Integer hits;

    private Integer count;

    @Column(nullable = false)
    private Boolean recruit;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private TbPost post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id")
    private TbUser user;

    @OneToOne(fetch = FetchType.LAZY)
    private TbMountain mountain;

    @OneToMany(mappedBy = "mate")
    private List<TbMateComment> mateComments = new ArrayList<>();

    @OneToMany(mappedBy = "mate")
    private List<TbMateFile> mateFiles = new ArrayList<>();

}