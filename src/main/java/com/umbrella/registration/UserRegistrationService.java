package com.umbrella.registration;

import com.umbrella.entity.Role;
import com.umbrella.entity.User;
import com.umbrella.repository.RoleRepository;
import com.umbrella.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public User registerNewUser(UserRegistrationDTO registrationDTO) {
        if (userRepository.existsByEmail(registrationDTO.getEmail())) {
            throw new RuntimeException("Este correo ya está registrado.");
        }

        Role userRole = roleRepository.findById(2)
                .orElseThrow(() -> new RuntimeException("Rol USER no encontrado"));

        User user = new User();
        user.setName(registrationDTO.getName());
        user.setLastName(registrationDTO.getLastName());
        user.setEmail(registrationDTO.getEmail());
        user.setPasswordHash(passwordEncoder.encode(registrationDTO.getPassword()));
        user.setRole(userRole);


        return userRepository.save(user);
    }


}
