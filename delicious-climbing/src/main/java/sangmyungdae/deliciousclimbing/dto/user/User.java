package sangmyungdae.deliciousclimbing.dto.user;

import lombok.*;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.Difficulty;
import sangmyungdae.deliciousclimbing.domain.enums.Gender;
import sangmyungdae.deliciousclimbing.domain.enums.LoginType;
import sangmyungdae.deliciousclimbing.dto.address.Address;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private LocalDate birthday;
    private String createdAt;
    private FamousMountain famousMountain;
    private Address address;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Setter
    public static class FamousMountain {
        private Long id;
        private String mountainName;

        public FamousMountain(TbFamousMountain entity) {
            if (entity != null) {
                this.id = entity.getId();
                this.mountainName = entity.getName();
            } else {
                this.id = null;
                this.mountainName = null;
            }
        }
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
        this.createdAt = entity.getCreatedAt();
        this.famousMountain = new FamousMountain(entity.getFamousMountain());
        this.address = new Address(entity.getAddress());
   }

}
