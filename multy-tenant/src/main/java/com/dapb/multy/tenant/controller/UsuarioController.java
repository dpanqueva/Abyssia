package com.dapb.multy.tenant.controller;

import com.dapb.multy.tenant.entity.Users;
import com.dapb.multy.tenant.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UsuarioController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        return ResponseEntity.created(URI.create("/api/v1/users/" + user.getUsername()))
                .body(userService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Users> login(@RequestBody Users user) {
        return ResponseEntity.ok(userService.login(user));
    }
}
