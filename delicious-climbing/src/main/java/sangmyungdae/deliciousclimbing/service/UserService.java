package sangmyungdae.deliciousclimbing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.dto.user.*;
import sangmyungdae.deliciousclimbing.repository.AddressRepository;
import sangmyungdae.deliciousclimbing.repository.FamousMountainRepository;
import sangmyungdae.deliciousclimbing.repository.UserRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final FamousMountainRepository famousMountainRepository;
    private final AddressRepository addressRepository;

    // private final PassworodEncoder passworodEncoder;

    @Transactional
    public User login (UserSign dto) {
        TbUser entity = userRepository.findByEmail(dto.getEmail()).orElseThrow();
        if(!entity.getPassword().equals(dto.getPassword())) {
            return null;
        }
        return User.builder()
                .entity(entity)
                .build();
    }

    @Transactional
    public User getUser(Long id) {
        TbUser entity = userRepository.findById(id).orElseThrow();

        return User.builder()
                .entity(entity)
                .build();
    }

    @Transactional
    public UserRegister createUser(UserRegister dto) {
        TbUser entity = userRepository.save(dto.toEntity());
        // Entity to Response DTO
        return UserRegister.builder()
                .entity(entity)
                .build();
    }

    @Transactional
    public User updateUser(Long id, UserDto dto) {
        TbUser entity = userRepository.findById(id).orElseThrow();
        TbFamousMountain famousMountain = famousMountainRepository.findById(dto.getFamousMountainId()).orElse(null);
        TbAddress address = addressRepository.findById(dto.getAddressCode()).orElse(null);

        entity.updateInfo(dto.getNickname(), dto.getImageUrl(), dto.getIntroduction(), dto.getDifficulty(),
                dto.getSns(), famousMountain, address);

        return User.builder().entity(entity).build();
    }

    @Transactional
    public void deleteUser(Long id, UserPassword dto) {
        TbUser entity = userRepository.findById(id).orElseThrow();

        // 기존 패스워드 확인
        if(!entity.getPassword().equals(dto.getOldPassword())) {
            // 예외 처리
        }

        userRepository.deleteById(id);
    }

    @Transactional
    public User changePassword(Long id, UserPassword dto) {
        TbUser entity = userRepository.findById(id).orElseThrow();

        // 기존 패스워드 확인
        if(!entity.getPassword().equals(dto.getOldPassword())) {
            // 예외 처리
        }

        // 확인 후, 새로운 패스워드 변경
        entity.updatePassword(dto.getNewPassword());

        return User.builder().entity(entity).build();
    }

    @Transactional
    public boolean existEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
