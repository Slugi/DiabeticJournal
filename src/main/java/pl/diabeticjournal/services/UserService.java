package pl.diabeticjournal.services;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.diabeticjournal.entity.Role;
import pl.diabeticjournal.entity.User;
import pl.diabeticjournal.repository.RoleRepository;
import pl.diabeticjournal.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow();
    }

    public User findUserbyUserName(String userName){
        return userRepository.findByUserName(userName).orElseThrow();
    }

    public User saveUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }
}
