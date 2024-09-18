package com.CapStone.Wellness_Service.Security;

import com.CapStone.Wellness_Service.Controller.LoginRequest;
import com.CapStone.Wellness_Service.Controller.RegisterRequest;
import com.CapStone.Wellness_Service.DTO.UserDTO;
import com.CapStone.Wellness_Service.Entity.AppUserRole;
import com.CapStone.Wellness_Service.Entity.User;
import com.CapStone.Wellness_Service.Repository.UserRepository;
import com.CapStone.Wellness_Service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder, MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mappingJackson2HttpMessageConverter = mappingJackson2HttpMessageConverter;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<?,?>> login(@RequestBody LoginRequest loginRequest){
        Map<Object,Object> response = new HashMap<>();response.put("token", "");response.put("authenticated", false);
        Authentication authRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(loginRequest.username(), loginRequest.password());
        Authentication authResult = authenticationManager.authenticate(authRequest);
        if (authResult.isAuthenticated()) {
            UserDetails user = userService.loadUserByUsername(loginRequest.username());
            response.put("token", jwtUtil.generateToken(user));
            response.put("authenticated", true);
        }
        response.put("status", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterRequest registerRequest) {
        var user = new User();
        user.setUsername(registerRequest.username());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setEmail(registerRequest.email());
        Set<AppUserRole> roles = Arrays.stream(registerRequest.roles())
                                .map(s -> new AppUserRole("ROLE_"+s))
                                .collect(Collectors.toSet());
        user.setRoles(roles);
        var response = userRepository.save(user);
        return ResponseEntity.ok(response);
    }


}
