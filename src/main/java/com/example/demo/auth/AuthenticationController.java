package com.example.demo.auth;

import com.example.demo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PutMapping("/{email}")
    public ResponseEntity<String> changeUserPassword(@PathVariable String email,
                                   @RequestParam String previousPassword,
                                   @RequestParam String newPassword) {
        try {
            service.changeUserPassword(email, previousPassword, newPassword);
            return ResponseEntity.ok().body("Password for user " + email + " changed");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }

    @DeleteMapping("/{email}")
    public void removeUser(@PathVariable String email) {
        service.removeUser(email);
    }

    @PutMapping("/grant-admin/{email}")
    public void grantAdmin(@PathVariable String email) {
        service.grantAdmin(email);
    }

    @PutMapping("/revoke-admin/{email}")
    public void revokeAdmin(@PathVariable String email) {
        service.revokeAdmin(email);
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return service.getUsers();
    }
}