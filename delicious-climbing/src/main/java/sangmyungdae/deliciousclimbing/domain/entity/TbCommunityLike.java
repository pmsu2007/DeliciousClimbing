package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "TB_COMMUNITY_LIKE")
public class TbCommunityLike extends TbDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private TbUser user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private TbPost post;

    @Builder
    public TbCommunityLike(TbUser user, TbPost post) {
        this.user = user;
        this.post = post;
    }
}
