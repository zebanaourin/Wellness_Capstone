package com.CapStone.Wellness_Service.Security;


import com.CapStone.Wellness_Service.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

//    public User saveUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypting password
//        return userRepository.save(user);
//    }

//    public Optional<User> findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }

//    public UserDetails loadUserByUsername(String username) {
//        Optional<User> user = userRepository.findByUsername(username);
//        if (!user.isPresent()) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//        return new org.springframework.security.core.userdetails.
//                User(user.get().getUsername(), user.get().getPassword(), new ArrayList<>());
//    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var dbuser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        return User
                .builder()
                .username(dbuser.getUsername())
                .password(dbuser.getPassword())
                .authorities(dbuser.getRoles().stream().map(role -> role.getName()).toArray(String[]::new))
                .build();
    }
}