package org.example.security;




import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordUpdateService {

    @Autowired
    private UserRepository usersRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void updatePasswords() {
        List<User> users = usersRepo.findAll();
        for (User user : users) {
            String password = user.getPassword();
            if (password != null && !password.startsWith("{bcrypt}")) {
                user.setPassword(passwordEncoder.encode(password));
                usersRepo.save(user);
            }
        }
    }
}

