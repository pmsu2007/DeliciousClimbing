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
    private int hits;

    @Column(columnDefinition = "integer default 0")
    private int likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private TbUser user;

    @OneToMany(mappedBy = "post")
    private List<TbComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TbFile> files = new ArrayList<>();


    @Builder
    public TbPost(BoardType type, String title, String content, TbUser user) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void addFile(TbFile file) {
        this.files.add(file);
        if (file.getPost() != this) {
            file.addPost(this);
        }
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateLike(int likes) {
        this.likes = likes;
    }

    public void updateHit(int hits) {
        this.hits = hits;
    }

}
