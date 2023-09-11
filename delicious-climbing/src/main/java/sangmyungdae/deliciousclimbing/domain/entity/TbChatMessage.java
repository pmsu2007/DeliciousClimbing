package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "TB_CHAT_MESSAGE")
public class TbChatMessage extends TbDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private TbChatRoom room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private TbUser user;

    @Builder
    public TbChatMessage(String content, TbChatRoom room, TbUser user) {
        this.content = content;
        this.room = room;
        this.user = user;
    }
}
