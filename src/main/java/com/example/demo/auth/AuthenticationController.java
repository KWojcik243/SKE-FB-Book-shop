package com.example.demo.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/logout")
    public ResponseEntity<AuthenticationResponse> logout(
            @RequestBody LogoutRequest request
    ) {
        return ResponseEntity.ok(service.logout(request));
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
}
