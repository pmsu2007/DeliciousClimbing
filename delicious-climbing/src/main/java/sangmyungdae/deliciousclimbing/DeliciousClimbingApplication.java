package sangmyungdae.deliciousclimbing;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // Auditing 사용
@EnableBatchProcessing // 배치 사용
@SpringBootApplication
public class DeliciousClimbingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliciousClimbingApplication.class, args);
	}

}
