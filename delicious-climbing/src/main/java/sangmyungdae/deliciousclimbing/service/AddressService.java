package sangmyungdae.deliciousclimbing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sangmyungdae.deliciousclimbing.dto.address.Address;
import sangmyungdae.deliciousclimbing.repository.AddressRepository;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    @Transactional
    public Address getAddress(Long code) {
        sangmyungdae.deliciousclimbing.domain.entity.TbAddress entity = addressRepository.findByCode(code).orElseThrow();

        return Address.builder()
                .entity(entity)
                .build();
    }

    @Transactional
    public Address createAddress(TbAddress dto) {
        sangmyungdae.deliciousclimbing.domain.entity.TbAddress entity = addressRepository.save(dto.toEnity());

        return Address.builder()
                .entity(entity)
                .build();
    }

    @Transactional
    public void deleteAddress(Long code) {
        addressRepository.deleteById(code);
    }
}
