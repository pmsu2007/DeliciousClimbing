package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sangmyungdae.deliciousclimbing.domain.enums.Difficulty;
import sangmyungdae.deliciousclimbing.domain.enums.Gender;
import sangmyungdae.deliciousclimbing.domain.enums.LoginType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "TB_USER")
public class TbUser extends TbDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoginType type;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "image_url")
    private String imageUrl;

    private String introduction;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private String sns;

    @Enumerated(EnumType.STRING)
    // @Column(nullable = false)
    private Gender gender;

    // @Column(nullable = false)
    private LocalDateTime birthday;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mountain_id")
    private TbFamousMountain famousMountain;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private TbAddress address;

    @OneToMany(mappedBy = "user")
    private List<TbUserReview> userReviews = new ArrayList<>();

}
