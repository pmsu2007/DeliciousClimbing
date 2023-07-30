package sangmyungdae.deliciousclimbing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sangmyungdae.deliciousclimbing.config.auth.AuthUtil;
import sangmyungdae.deliciousclimbing.domain.entity.TbAddress;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.dto.common.FileDto;
import sangmyungdae.deliciousclimbing.dto.user.*;
import sangmyungdae.deliciousclimbing.repository.AddressRepository;
import sangmyungdae.deliciousclimbing.repository.FamousMountainRepository;
import sangmyungdae.deliciousclimbing.repository.UserRepository;
import sangmyungdae.deliciousclimbing.util.ExceptionUtil;
import sangmyungdae.deliciousclimbing.util.FileStore;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final FamousMountainRepository famousMountainRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManager;
    private final FileStore fileStore;

    private TbUser findUser(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> ExceptionUtil.id(username, TbUser.class.getName()));
    }

    private TbUser findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> ExceptionUtil.id(id, TbUser.class.getName()));
    }

    private TbFamousMountain findFamousMountain(Long id) {
        return famousMountainRepository.findById(id)
                .orElseThrow(() -> ExceptionUtil.id(id, TbFamousMountain.class.getName()));
    }

    private TbAddress findAddress(Long code) {
        return addressRepository.findByCode(code)
                .orElseThrow(() -> ExceptionUtil.id(code, TbAddress.class.getName()));
    }


    @Transactional
    public User login(UserSign dto) {
        TbUser user = findUser(dto.getEmail());

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Bad Credential");
        }

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());

        Authentication authentication = authenticationManager.getObject().authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return User.builder()
                .entity(user)
                .build();
    }

    @Transactional
    public User getUser() {
        TbUser user = findUser(AuthUtil.getAuthUser());

        return User.builder()
                .entity(user)
                .build();
    }

    @Transactional
    public User getUserById(Long id) {
        TbUser user = findUser(id);

        return User.builder()
                .entity(user)
                .build();
    }

    @Transactional
    public UserRegister createUser(UserRegister dto) {
        TbUser user = dto.toEntity();
        user.updatePassword(passwordEncoder.encode(dto.getPassword()));

        // Entity to Response DTO
        return UserRegister.builder()
                .entity(userRepository.save(user))
                .build();
    }

    @Transactional
    public void updateUser(UserDto dto) {
        TbUser user = findUser(AuthUtil.getAuthUser());
        TbFamousMountain famousMountain = findFamousMountain(dto.getFamousMountainId());
        TbAddress address = findAddress(dto.getAddressCode());

        user.updateInfo(dto.getNickname(), dto.getIntroduction(), dto.getDifficulty(),
                dto.getSns(), famousMountain, address);

        userRepository.save(user);
    }

    @Transactional
    public void updateUserImage(MultipartFile multipartFile) {
        TbUser user = findUser(AuthUtil.getAuthUser());

        if (multipartFile == null || multipartFile.isEmpty()) {
            throw ExceptionUtil.require("No MultipartFile");
        }

        FileDto file = fileStore.storeFile(multipartFile);
        user.updateImage(file.getStoreFileName(), file.getUploadFileName());
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String currentPassword) {
        TbUser user = findUser(AuthUtil.getAuthUser());
        // 기존 패스워드 확인
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new BadCredentialsException("Bad Credential");
        }

        userRepository.delete(user);
    }

    @Transactional
    public User changePassword(UserPassword dto) {
        TbUser user = findUser(AuthUtil.getAuthUser());
        // 기존 패스워드 확인
        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            throw new BadCredentialsException("Bad Credential");
        }
        // 확인 후, 새로운 패스워드 변경
        user.updatePassword(passwordEncoder.encode(dto.getNewPassword()));

        return User.builder()
                .entity(userRepository.save(user))
                .build();
    }

    @Transactional
    public boolean existEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
