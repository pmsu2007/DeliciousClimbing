package sangmyungdae.deliciousclimbing.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sangmyungdae.deliciousclimbing.domain.entity.TbUser;
import sangmyungdae.deliciousclimbing.repository.UserRepository;
import sangmyungdae.deliciousclimbing.util.ExceptionUtil;

@Service
@RequiredArgsConstructor
public class AuthUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        TbUser user = userRepository.findByEmail(username).orElseThrow(
                () -> ExceptionUtil.id(username, TbUser.class.getName())
        );

        return AuthUser.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .role(user.getRole().getName())
                .build();
    }
}
