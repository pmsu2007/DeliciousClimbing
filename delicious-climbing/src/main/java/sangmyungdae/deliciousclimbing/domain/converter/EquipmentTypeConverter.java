package sangmyungdae.deliciousclimbing.domain.converter;

import org.springframework.core.convert.converter.Converter;
import sangmyungdae.deliciousclimbing.domain.enums.BoardType;
import sangmyungdae.deliciousclimbing.domain.enums.EquipmentType;
public class EquipmentTypeConverter implements Converter<String, EquipmentType> {

    @Override
    public EquipmentType convert(String source) {
        try {
            return EquipmentType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}