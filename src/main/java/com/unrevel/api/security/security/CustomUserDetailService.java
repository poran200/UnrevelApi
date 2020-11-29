package com.unrevel.api.security.security;


import com.unrevel.api.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserDetailsService")
public class CustomUserDetailService implements UserDetailsService {
    final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUserNameIsAndIsActiveTrue(s)
                .map(CUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User name not found : " + s));
    }
}
