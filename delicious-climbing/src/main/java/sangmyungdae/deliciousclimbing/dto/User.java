package sangmyungdae.deliciousclimbing.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.Difficulty;
import sangmyungdae.deliciousclimbing.domain.enums.Gender;
import sangmyungdae.deliciousclimbing.domain.enums.LoginType;

import java.time.LocalDateTime;

@Getter
@ToString
public class User {
    private long id;
    private String email;
    private String password;
    private LoginType type;
    private String nickname;
    private String imageUrl;
    private String introduction;
    private Difficulty difficulty;
    private String sns;
    private Gender gender;
    private LocalDateTime birthday;
    private String famousMountain;
    private Long address;

    @Builder
    public User(TbUser entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.type = entity.getType();
        this.nickname = entity.getNickname();
        this.imageUrl = entity.getImageUrl();
        this.introduction = entity.getIntroduction();
        this.difficulty = entity.getDifficulty();
        this.sns = entity.getSns();
        this.gender = entity.getGender();
        this.birthday = entity.getBirthday();
        this.famousMountain = entity.getFamousMountain().getName();
        this.address = entity.getAddress().getCode();
    }
}
