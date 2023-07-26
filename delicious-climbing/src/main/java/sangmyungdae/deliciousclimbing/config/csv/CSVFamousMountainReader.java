package sangmyungdae.deliciousclimbing.config.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;

@Configuration
@RequiredArgsConstructor
public class CSVFamousMountainReader {
    @Bean
    public FlatFileItemReader<TbFamousMountain> csvFamousMountainFileReader() {
        // File Read
        FlatFileItemReader<TbFamousMountain> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource("/csv/famousMountain.csv"));
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setEncoding("UTF-8");

        // Read할 데이터를 LineMapper를 통해 Mapping
        DefaultLineMapper<TbFamousMountain> defaultLineMapper = new DefaultLineMapper<>();

        // setNames를 통해 데이터의 key 이름을 설정
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(",");
        delimitedLineTokenizer.setNames("id", "name", "height");
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);

        // Tokenizer에서 가져온 데이터들을 TbFamousMountain에 바인딩
        BeanWrapperFieldSetMapper<TbFamousMountain> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(TbFamousMountain.class);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        // lineMapper 설정
        flatFileItemReader.setLineMapper(defaultLineMapper);

        return flatFileItemReader;
    }
}
