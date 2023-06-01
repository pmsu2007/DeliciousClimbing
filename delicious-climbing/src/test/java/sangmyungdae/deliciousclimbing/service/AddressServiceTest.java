package sangmyungdae.deliciousclimbing.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sangmyungdae.deliciousclimbing.dto.address.Address;

@SpringBootTest
public class AddressServiceTest {
    @Autowired
    private AddressService addressService;

    @Test
    @DisplayName(value = "주소 조회")
    void getAddress() {
        // when
        Address address = addressService.getAddress(1114000000L);
        // then
        System.out.println("address = " + address);
    }
}
