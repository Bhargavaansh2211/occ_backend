package com.example.occ.event;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.occ.event.EventModel;

public interface EventRepository extends JpaRepository<EventModel, Integer>{

	EventModel findByEventId(int eventId);
	

}