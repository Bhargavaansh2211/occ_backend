package com.example.occ.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.occ.event.*;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

   
    public EventModel createEvent(EventModel eventModel){
        return this.eventRepository.save(eventModel);
    }
    
    public List<EventModel> getAllEvents() {
		
		return this.eventRepository.findAll();
	}
}
