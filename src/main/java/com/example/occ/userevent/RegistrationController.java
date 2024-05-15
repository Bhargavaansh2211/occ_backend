package com.example.occ.userevent;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.occ.userevent.UserEventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/event")
public class RegistrationController {

    private UserEventService userEventService;

    public RegistrationController(UserEventService userEventService) {
        this.userEventService = userEventService;
    }

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<String> registerUserToEvent(@RequestBody RegistrationRequest request) {
        try {
            userEventService.registerUserToEvent(request.getUserId(), request.getEventId());
            return ResponseEntity.ok("User registered to event successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to register user to event: " + e.getMessage());
        }
    }

    @CrossOrigin
    @GetMapping("/registrationStatus/{userId}/{eventId}")
    public ResponseEntity<Boolean> checkUserRegistrationStatus(@PathVariable int userId, @PathVariable int eventId) {
        try {
            boolean isRegistered = userEventService.checkUserRegistrationStatus(userId, eventId);
            return ResponseEntity.ok(isRegistered);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(false);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(false);
        }
    }

    @CrossOrigin
    @PostMapping("/unregister")
    public ResponseEntity<String> unregisterUserFromEvent(@RequestBody RegistrationRequest request) {
        try {
            userEventService.unregisterUserFromEvent(request.getUserId(), request.getEventId());
            return ResponseEntity.ok("User unregistered from event successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to unregister user from event: " + e.getMessage());
        }
    }

}
