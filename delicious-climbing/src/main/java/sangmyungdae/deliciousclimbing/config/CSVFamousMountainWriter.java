package sangmyungdae.deliciousclimbing.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.repository.FamousMountainRepository;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CSVFamousMountainWriter implements ItemWriter<TbFamousMountain> {

    private final FamousMountainRepository famousMountainRepository;

    @Override
    public void write(List<? extends TbFamousMountain> items) throws Exception {
        famousMountainRepository.saveAll(new ArrayList<TbFamousMountain>(items));
    }
}
