package sangmyungdae.deliciousclimbing.dto.user;

import lombok.*;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.Difficulty;

@Getter @Setter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String nickname;
    private String imageUrl;
    private String introduction;
    private Difficulty difficulty;
    private String sns;
    private Long famousMountainId;
    private Long addressCode;

    @Builder
    public UserDto(Long id, String nickname, String imageUrl, String introduction, Difficulty difficulty,
                   String sns, Long famousMountainId, Long addressCode) {
        this.id = id;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.introduction = introduction;
        this.difficulty = difficulty;
        this.sns = sns;
        this.famousMountainId = famousMountainId;
        this.addressCode = addressCode;
    }

    public TbUser toEntity(TbFamousMountain famousMountain, TbAddress address) {
        return TbUser.builder()
                .nickname(this.nickname)
                .imageUrl(this.imageUrl)
                .introduction(this.introduction)
                .difficulty(this.difficulty)
                .sns(this.sns)
                .famousMountain(famousMountain)
                .address(address)
                .build();
    }
}
