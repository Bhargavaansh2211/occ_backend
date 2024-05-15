package com.example.occ.userevent;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.occ.event.EventModel;
import com.example.occ.user.UserModel;


public interface UserEventRepository extends JpaRepository<UserEvent, Integer> {
    boolean existsByUserAndEvent(UserModel user, EventModel event);

    UserEvent findByUserAndEvent(UserModel user, EventModel event);

    List<UserEvent> findByEvent(EventModel event);


}