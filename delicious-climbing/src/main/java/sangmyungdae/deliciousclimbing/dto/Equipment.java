package sangmyungdae.deliciousclimbing.dto;

import lombok.*;
import org.springframework.data.domain.Page;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipment;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.domain.enums.EquipmentType;
import sangmyungdae.deliciousclimbing.dto.address.Address;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString //출력할때이쁘게해줌
public class Equipment {

    private long id;
    private  String title;
    private String content;
    private int hits;
    private int tradeCost;
    private EquipmentType type;
    private Boolean tradeStatus;
    private Address address;
    private User user;
    private List<EquipmentFile> equipmentFiles = new ArrayList<>();
    private String createdAt;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Setter
    public static class User{   // inner class 로 만들어 주는 이유 : select query를 한번만 호출하고 필요한 데이터만 보여줄 수 있음
        private Long id;        // user엔 이 변수들말고 어어엄청 다양하니까!
        private String author;
        private String imageUrl;

    public User(TbUser user) {
        this.id = user.getId();
        this.author = user.getNickname();
        this.imageUrl = user.getStoreFileName();
    }
}

    @Builder
    public Equipment (TbEquipment entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.hits = entity.getHits();
        this.tradeCost = entity.getTradeCost();
        this.type = entity.getType();
        this.tradeStatus = entity.isTradeStatus();
        this.createdAt = entity.getCreatedAt();
        this.address = new Address(entity.getAddress());
        this.user = new User(entity.getUser());

    }

    public static Page<Equipment> toDtoList(Page<TbEquipment>entities){
        Page<Equipment> equipments = entities.map(entity ->
                Equipment.builder().entity(entity).build());

        return equipments;
    }
}