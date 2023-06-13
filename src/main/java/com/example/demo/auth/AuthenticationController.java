package com.example.demo.auth;

import com.example.demo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PutMapping("/{email}")
    public ResponseEntity<String> changeUserPassword(@PathVariable String email,
                                                     @RequestParam String previousPassword,
                                                     @RequestParam String newPassword) {
        try {
            service.changeUserPassword(email, previousPassword, newPassword);
            return ResponseEntity.ok().body("Password for user " + email + " changed");
        } catch (Exception e) {
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
    public ResponseEntity<CollectionModel<EntityModel<User>>> getUsers() {
        List<User> users = service.getUsers();

        List<EntityModel<User>> userModels = users.stream()
                .map(user -> EntityModel.of(user,
                        Link.of("/auth/users/" + user.getId()).withSelfRel()))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<User>> usersResource = CollectionModel.of(userModels);
        usersResource.add(Link.of("/auth/users").withSelfRel());

        return ResponseEntity.ok(usersResource);
    }
}
