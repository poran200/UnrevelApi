package com.unrevel.api.utill;

import com.unrevel.api.model.Role;
import com.unrevel.api.model.User;
import com.unrevel.api.repository.RoleRepository;
import com.unrevel.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
class DbInit {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @PostConstruct
    private void postConstruct() {
        User user = new User();
      user.setUserName("admin");
      user.setPassword(passwordEncoder.encode("admin"));
      user.setFullName("admin user");
      user.setEmail("admin@gamil.com");
      user.setIsActive(true);
      user.setAccountNotLocked(true);
      user.addRole(new Role(SecurityConstants.ADMIN));
      userRepository.save(user);
    }
}
