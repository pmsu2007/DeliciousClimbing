package sangmyungdae.deliciousclimbing.config.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;
import sangmyungdae.deliciousclimbing.repository.AddressRepository;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CSVAddressWriter implements ItemWriter<TbAddress> {

    private final AddressRepository addressRepository;


    @Override
    public void write(List<? extends TbAddress> items) throws Exception {
        addressRepository.saveAll(new ArrayList<TbAddress>(items));
    }
}
