package sangmyungdae.deliciousclimbing.domain.converter;

import org.springframework.core.convert.converter.Converter;
import sangmyungdae.deliciousclimbing.domain.enums.BoardType;

public class BoardTypeConverter implements Converter<String, BoardType> {

    @Override
    public BoardType convert(String source) {
        try {
            return BoardType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
