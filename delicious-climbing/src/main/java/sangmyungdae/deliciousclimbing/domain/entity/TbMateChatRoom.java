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
@Table(name = "TB_MATE_CHATROOM")
public class TbMateChatRoom extends TbDateEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "current_count")
    private int currentCount;

    @Column(name = "total_count")
    private int totalCount;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mate_id")
    private TbMate mate;

    @OneToMany(mappedBy = "room")
    private List<TbMateChat> chats = new ArrayList<>();

    @OneToMany(mappedBy = "room")
    private List<TbMateChatMessage> messages = new ArrayList<>();

    @Builder
    public TbMateChatRoom(String roomName, int currentCount, int totalCount, TbMate mate) {
        this.roomName = roomName;
        this.currentCount = currentCount;
        this.totalCount = totalCount;
        this.mate = mate;
    }

    public void updateCurrentCount(int count) {
        this.currentCount = count;
    }

    public void addMate(TbMate mate) { this.mate = mate; }
}
