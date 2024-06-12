package org.example.security;


import jakarta.transaction.Transactional;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository usersRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userses = usersRepo.findByUsername(username);
        return userses.map(user -> {
            return new MyUserDetails(user);
        }).orElseThrow(() ->
                new UsernameNotFoundException(username + " not such user "));
    }
}


