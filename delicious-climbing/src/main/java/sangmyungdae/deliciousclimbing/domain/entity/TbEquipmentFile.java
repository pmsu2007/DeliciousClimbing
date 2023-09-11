package sangmyungdae.deliciousclimbing.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "TB_EQUIPMENT_FILE")
public class TbEquipmentFile extends TbDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "upload_file_name", nullable = false)
    private String uploadFileName;

    @Column(name = "store_file_name", nullable = false)
    private String storeFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private TbEquipment equipment;

    @Builder
    public TbEquipmentFile(TbEquipment equipment, String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.equipment = equipment;
    }


    public void addPost(TbEquipment equipment){
        this.equipment = equipment;
    }
}