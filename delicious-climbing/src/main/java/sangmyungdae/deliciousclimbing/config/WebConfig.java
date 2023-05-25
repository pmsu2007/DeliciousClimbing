package sangmyungdae.deliciousclimbing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sangmyungdae.deliciousclimbing.domain.converter.BoardTypeConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters (FormatterRegistry registry) {
        registry.addConverter(new BoardTypeConverter());
    }
}
