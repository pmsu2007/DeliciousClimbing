package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sangmyungdae.deliciousclimbing.domain.enums.ReviewType;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "TB_REVIEW")
public class TbReview extends TbDateEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private ReviewType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id")
    private TbUser reviewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private TbUser user;

    @Builder
    public TbReview(String content, ReviewType type, TbUser reviewer, TbUser user) {
        this.content = content;
        this.type = type;
        this.reviewer = reviewer;
        this.user = user;
    }
}
