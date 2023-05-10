package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sangmyungdae.deliciousclimbing.domain.enums.BoardType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "TB_POST")
public class TbPost extends TbDateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardType type;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(columnDefinition = "integer default 0")
    private Integer likes;

    @Column(columnDefinition = "integer default 0")
    private Integer hits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private TbUser user;

    @OneToMany(mappedBy = "post")
    private List<TbComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<TbFile> files = new ArrayList<>();

    @Builder
    public TbPost(BoardType type, String title, String content, Integer likes, Integer hits, TbUser user) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.likes = likes;
        this.hits = hits;
        this.user = user;
    }
}
