package com.aciornii.library.service;

import com.aciornii.library.domain.Role;
import com.aciornii.library.domain.User;
import com.aciornii.library.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        if (userRepository.findByUsername("user") == null) {
            User user = User.builder()
                    .username("user")
                    .password("password")
                    .authorities(Collections.unmodifiableList(Arrays.asList(Role.USER)))
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .enabled(true)
                    .build();
            userRepository.save(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username);
        return User.builder()
                .username(username)
                .password("password")
                .authorities(Collections.unmodifiableList(Arrays.asList(Role.USER)))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }

}
