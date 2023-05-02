package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(columnDefinition = "integer default 0")
    private Integer hits;

    @Column(name = "recruit_count", nullable = false)
    private Integer recruitCount;

    @Column(name = "recruit_status", nullable = false)
    private Boolean recruitStatus;

    @Column(name = "recruit_date", nullable = false)
    private LocalDateTime recruitDate;

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

    @Builder

    public TbMate(String title, String content, Integer hits, Integer recruitCount, Boolean recruitStatus,
                  LocalDateTime recruitDate, TbPost post, TbUser user, TbMountain mountain) {
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.recruitCount = recruitCount;
        this.recruitStatus = recruitStatus;
        this.recruitDate = recruitDate;
        this.post = post;
        this.user = user;
        this.mountain = mountain;
    }
}