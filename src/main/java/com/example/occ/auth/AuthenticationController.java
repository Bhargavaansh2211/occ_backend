package com.example.occ.auth;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.occ.user.UserModel;
import com.example.occ.user.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  private final AuthenticationService service;

  @CrossOrigin
  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody Map<String, String> requestData) {
    String userHandle = requestData.get("userHandle");
    String name = requestData.get("name");
    String email = requestData.get("email");
    String password = requestData.get("password");

    return ResponseEntity.ok(service.register(name, userHandle, email, password));
  }

  @CrossOrigin
  @PostMapping("/authenticate")
  public ResponseEntity<String> authenticate(
      @RequestBody Map<String, String> requestData) {
    String email = requestData.get("email");
    String password = requestData.get("password");
    return ResponseEntity.ok(service.authenticate(email, password));
  }
  

}
