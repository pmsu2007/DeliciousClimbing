package sangmyungdae.deliciousclimbing.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;

@Configuration
@RequiredArgsConstructor
public class CSVAddressReader {

    @Bean
    public FlatFileItemReader<TbAddress> csvAddressFileReader() {
        // File Read
        FlatFileItemReader<TbAddress> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource("/csv/address.csv"));
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setEncoding("UTF-8");

        // Read할 데이터를 LineMapper를 통해 Mapping
        DefaultLineMapper<TbAddress> defaultLineMapper = new DefaultLineMapper<>();

        // setNames를 통해 데이터의 key 이름을 설정
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(",");
        delimitedLineTokenizer.setNames("code", "sido", "sigungu");
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);

        // Tokenizer에서 가져온 데이터들을 TbAddress에 바인딩
        BeanWrapperFieldSetMapper<TbAddress> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(TbAddress.class);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        // lineMapper 설정
        flatFileItemReader.setLineMapper(defaultLineMapper);

        return flatFileItemReader;
    }
}
