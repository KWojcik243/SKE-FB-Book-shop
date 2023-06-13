package com.example.demo.auth;

import com.example.demo.config.JwtService;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Role;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request){
        var user = new User(request.getName(),request.getSurname(),request.getEmail(),passwordEncoder.encode(request.getPassword()),Role.USER);
        Cart cart = new Cart(user);
        user.setCart(cart);
        repository.save(user);
        cartRepository.save(cart);
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse logout(LogoutRequest request){
        return AuthenticationResponse.builder().token(jwtService.setTokenExpired(request.token)).build();
    }
}
