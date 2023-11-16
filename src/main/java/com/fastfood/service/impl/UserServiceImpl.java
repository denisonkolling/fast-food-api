package com.fastfood.service.impl;

import com.fastfood.model.User;
import com.fastfood.repository.UserRepository;
import com.fastfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail()))
            throw new IllegalArgumentException("User already registered");
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
