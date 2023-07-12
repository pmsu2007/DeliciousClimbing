package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "TB_FILE")
public class TbFile extends TbDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "upload_filename", nullable = false)
    private String uploadFileName;

    @Column(name = "store_filename", nullable = false)
    private String storeFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private TbPost post;

    @Builder
    public TbFile(TbPost post, String uploadFileName, String storeFileName) {
        this.post = post;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

    public void addPost(TbPost post) {
        this.post = post;
    }
}