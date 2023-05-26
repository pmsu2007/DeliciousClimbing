package sangmyungdae.deliciousclimbing.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;

@Configuration
@RequiredArgsConstructor
public class FileReaderJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CSVReader csvReader;
    private final CSVAddressWriter csvAddressWriter;
    private static final int CHUNKSIZE = 100;

    @Bean
    public Job csvAddressFileReaderJob() {
        return jobBuilderFactory.get("csvAddressFileReaderJob")
                .start(csvAddressFileReaderStep())
                .build();
    }

    @Bean
    public Step csvAddressFileReaderStep() {
        return stepBuilderFactory.get("csvAddressFileReaderStep")
                .<TbAddress, TbAddress>chunk(CHUNKSIZE)
                .reader(csvReader.csvAddressFileReader())
                .writer(csvAddressWriter)
                .allowStartIfComplete(true)
                .build();
    }
}
