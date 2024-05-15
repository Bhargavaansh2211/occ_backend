package com.example.occ.userevent;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.occ.event.EventModel;
import com.example.occ.event.EventRepository;
import com.example.occ.user.UserModel;
import com.example.occ.user.UserRepository;

@Service
public class UserEventService {

    private EventRepository eventRepository;
    private UserRepository userRepository;
    private UserEventRepository userEventRepository;

    public UserEventService(EventRepository eventRepository, UserRepository userRepository,
            UserEventRepository userEventRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.userEventRepository = userEventRepository;
    }

    @Transactional
    public void registerUserToEvent(int userId, int eventId) {
        // Retrieve user and event from repositories
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        EventModel event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with id: " + eventId));

        // Check if the user is already registered for the event
        boolean isUserRegistered = userEventRepository.existsByUserAndEvent(user, event);
        if (isUserRegistered) {
            throw new IllegalArgumentException("User is already registered for the event");
        }

        UserEvent userEvent = new UserEvent();
        userEvent.setUser(user);
        userEvent.setEvent(event);
        userEventRepository.save(userEvent);
    }

    public boolean checkUserRegistrationStatus(int userId, int eventId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        EventModel event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with id: " + eventId));

        return userEventRepository.existsByUserAndEvent(user, event);
    }

    @Transactional
    public void unregisterUserFromEvent(int userId, int eventId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        EventModel event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with id: " + eventId));

        UserEvent userEvent = userEventRepository.findByUserAndEvent(user, event);
        if (userEvent == null) {
            throw new IllegalArgumentException("User is not registered for the event");
        }

        userEventRepository.delete(userEvent);
    }

   

}
