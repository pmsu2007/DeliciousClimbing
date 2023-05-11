package sangmyungdae.deliciousclimbing.dto.user;

import lombok.Builder;
import lombok.Getter;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.Difficulty;

@Getter
public class UserDto {
    private String nickname;
    private String imageUrl;
    private String introduction;
    private Difficulty difficulty;
    private String sns;
    private Long famousMountainId;
    private Long addressCode;

    @Builder
    public UserDto(String nickname, String imageUrl, String introduction, Difficulty difficulty,
                   String sns, Long famousMountainId, Long addressCode) {
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
