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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        for(User u: userRepository.findAll()){
            if(request.getEmail().equals(u.getEmail())){
                throw new RuntimeException("User with email " + request.getEmail() + " already exists.");
            }
        }


        var user = new User(request.getName(), request.getSurname(), request.getEmail(), passwordEncoder.encode(request.getPassword()), Role.USER);
        Cart cart = new Cart(user);
        user.setCart(cart);
        userRepository.save(user);
        cartRepository.save(cart);
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public void changeUserPassword(String email, String previousPassword, String newPassword) {
        User user = userRepository.findByEmail(email).orElse(null);

        String newPasswordHash = passwordEncoder.encode(newPassword);
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, previousPassword)
            );
            user.setPassword(newPasswordHash);
            userRepository.save(user);
        }
        catch (Exception e){
            throw new RuntimeException("Invalid username or password");
        }
    }

    public void removeUser(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            cartRepository.delete(user.getCart());
            userRepository.delete(user);
        } else {
            throw new RuntimeException("User with email " + email + " does not exist.");
        }
    }

    public void grantAdmin(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            user.get().grantAdminPrivileges();
            userRepository.save(user.get());
        } else {
            throw new RuntimeException("User with email " + email + " does not exist.");
        }
    }

    public void revokeAdmin(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            user.get().revokeAdminPrivileges();
            userRepository.save(user.get());
        } else {
            throw new RuntimeException("User with email " + email + " does not exist.");
        }
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
