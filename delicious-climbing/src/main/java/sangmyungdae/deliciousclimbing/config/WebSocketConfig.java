package sangmyungdae.deliciousclimbing.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

// 웹 소켓 연결을 위한 엔드포인트 설정 및 stomp sub/pub 엔드포인트 설정

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // stomp 접속 주소 url => /stomp/chat
        registry.addEndpoint("/stomp/chat") // endpoint
                .setAllowedOrigins("http://localhost:8080") // Cross Origin error
                .withSockJS(); // connet SocketJS
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // subscribe message url => 메시지 받을 때
        registry.enableSimpleBroker("/sub");

        // publish message url => 메시지 보낼 때
        registry.setApplicationDestinationPrefixes("/pub");
    }

}
