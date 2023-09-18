package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.*;
import sangmyungdae.deliciousclimbing.domain.enums.Difficulty;
import sangmyungdae.deliciousclimbing.domain.enums.Gender;
import sangmyungdae.deliciousclimbing.domain.enums.LoginType;
import sangmyungdae.deliciousclimbing.domain.enums.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "TB_USER")
@ToString
public class TbUser extends TbDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoginType type;

    @Column(nullable = false)
    private String nickname;

    private String introduction;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private String sns;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(name = "upload_filename")
    private String uploadFileName;

    @Column(name = "store_filename")
    private String storeFileName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mountain_id")
    private TbFamousMountain famousMountain;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private TbAddress address;

    @OneToMany(mappedBy = "user")
    private List<TbCommunityLike> communityLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<TbFamousMountainLike> famousMountainLikes = new ArrayList<>();

    @OneToMany(mappedBy = "participant")
    private List<TbMateChat> chatList = new ArrayList<>();

    @OneToMany(mappedBy = "creator")
    private List<TbEquipmentChat> equipmentChats = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<TbReview> receiveReviews = new ArrayList<>();

    @Builder
    public TbUser(String email, String password, Role role, LoginType type, String nickname, String introduction,
                  Difficulty difficulty, String sns, Gender gender, LocalDate birthday,
                  TbFamousMountain famousMountain, TbAddress address) {

        this.email = email;
        this.password = password;
        this.role = role;
        this.type = type;
        this.nickname = nickname;
        this.introduction = introduction;
        this.difficulty = difficulty;
        this.sns = sns;
        this.gender = gender;
        this.birthday = birthday;
        this.famousMountain = famousMountain;
        this.address = address;
    }

    public void updateInfo(String nickname, String introduction, Difficulty difficulty,
                           String sns, TbFamousMountain famousMountain, TbAddress address) {
        this.nickname = nickname;
        this.introduction = introduction;
        this.difficulty = difficulty;
        this.sns = sns;
        this.famousMountain = famousMountain;
        this.address = address;
    }

    public void updateImage(String storeFileName, String uploadFileName) {
        this.storeFileName = storeFileName;
        this.uploadFileName = uploadFileName;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
