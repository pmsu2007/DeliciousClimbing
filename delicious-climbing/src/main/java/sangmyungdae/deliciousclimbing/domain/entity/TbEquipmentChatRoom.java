package sangmyungdae.deliciousclimbing.domain.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "TB_EQUIPMENT_CHATROOM")
public class TbEquipmentChatRoom extends TbDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "current_count")
    private int currentCount;

    @Column(name = "total_count")
    private int totalCount;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private TbUser creator;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private TbEquipment equipment;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<TbEquipmentChat> chats = new ArrayList<>();

    @OneToMany(mappedBy = "room")
    private List<TbEquipmentChatMessage> messages = new ArrayList<>();

    @Builder
    public TbEquipmentChatRoom(String roomName, int currentCount, int totalCount, TbUser creator, TbEquipment equipment) {
        this.roomName = roomName;
        this.currentCount = currentCount;
        this.totalCount = totalCount;
        this.creator = creator;
        this.equipment = equipment;
    }

    public void updateCurrentCount(int count) {
        this.currentCount = count;
    }

    public void addEquipmentChat(TbEquipmentChat equipmentChat) {
        chats.add(equipmentChat);
    }
}
