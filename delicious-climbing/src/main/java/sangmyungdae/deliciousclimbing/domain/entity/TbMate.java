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
    private int hits;

    @Column(name = "recruit_count")
    private int recruitCount;

    @Column(name = "recruit_status")
    private boolean recruitStatus;

    @Column(name = "recruit_date", nullable = false)
    private LocalDateTime recruitDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id")
    private TbUser user;

    @OneToOne(fetch = FetchType.LAZY)
    private TbMountain mountain;

    @OneToMany(mappedBy = "mate")
    private List<TbMateComment> mateComments = new ArrayList<>();

    @OneToMany(mappedBy = "mate")
    private List<TbMateFile> mateFiles = new ArrayList<>();

    @OneToMany(mappedBy = "mate")
    private List<TbMateReview> mateReviews = new ArrayList<>();

    @Builder
    public TbMate(String title, String content, int hits, int recruitCount, boolean recruitStatus,
                  LocalDateTime recruitDate, TbUser user, TbMountain mountain) {
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.recruitCount = recruitCount;
        this.recruitStatus = recruitStatus;
        this.recruitDate = recruitDate;
        this.user = user;
        this.mountain = mountain;
    }

    public void update(String title, String content, int recruitCount, boolean recruitStatus, LocalDateTime recruitDate) {
        this.title = title;
        this.content = content;
        this.recruitCount = recruitCount;
        this.recruitStatus = recruitStatus;
        this.recruitDate = recruitDate;
    }
}