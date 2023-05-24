package sangmyungdae.deliciousclimbing.dto.user;

import lombok.*;
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
    private FamousMountain famousMountain;
    private Address address;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Setter
    public static class FamousMountain {
        private Long id;
        private String mountainName;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Setter
    public static class Address {
        private Long code;
        private String sido;
        private String sigungu;
    }

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
   }

    public void addFamousMountain(Long id, String moutainName) {
        this.famousMountain = new FamousMountain(id, moutainName);
    }

    public void addAddress(Long code, String sido, String sigungu) {
        this.address = new Address(code, sido, sigungu);
    }
}
