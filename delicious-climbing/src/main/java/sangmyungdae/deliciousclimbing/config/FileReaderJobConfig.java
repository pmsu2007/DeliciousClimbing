package sangmyungdae.deliciousclimbing.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;

@Configuration
@RequiredArgsConstructor
public class FileReaderJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CSVAddressReader csvAddressReader;
    private final CSVAddressWriter csvAddressWriter;
    private final CSVFamousMountainReader csvFamousMountainReader;
    private final CSVFamousMountainWriter csvFamousMountainWriter;
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
                .reader(csvAddressReader.csvAddressFileReader())
                .writer(csvAddressWriter)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Job csvFamousMountainFileReaderJob() {
        return jobBuilderFactory.get("csvFamousMountainFileReaderJob")
                .start(csvFamousMountainFileReaderStep())
                .build();
    }

    @Bean
    public Step csvFamousMountainFileReaderStep() {
        return stepBuilderFactory.get("csvFamousMountainFileReaderStep")
                .<TbFamousMountain, TbFamousMountain>chunk(CHUNKSIZE)
                .reader(csvFamousMountainReader.csvFamousMountainFileReader())
                .writer(csvFamousMountainWriter)
                .allowStartIfComplete(true)
                .build();
    }
}
