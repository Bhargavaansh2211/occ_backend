package com.example.occ.event;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.occ.event.*;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/event")

public class EventController {
    
    @Autowired
    private EventService eventService;


    private Vector<EventModel> eventVec;

    @CrossOrigin
    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<?> createEvent(
        @RequestParam("title") String title,
        @RequestParam("description") String description,
        @RequestParam("venue") String venue,
        @RequestParam("fees") String fees,
        @RequestParam("start_time") String startTime,
        @RequestParam("end_time") String endTime,
        @RequestParam("start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
        @RequestParam("end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
        @RequestPart("image") MultipartFile image) {

        eventVec = new Vector<>();

        try {
           
            EventModel eventModel = new EventModel();
            eventModel.setTitle(title);
            eventModel.setDescription(description);
            eventModel.setVenue(venue);
            eventModel.setFees(fees);
            eventModel.setStart_time(startTime);
            eventModel.setEnd_time(endTime);
            eventModel.setStart_date(startDate);
            eventModel.setEnd_date(endDate);
            
          
            if (image != null && !image.isEmpty()) {
                eventModel.setImage(image.getBytes());
            }
            
            EventModel createdEvent = this.eventService.createEvent(eventModel);
            eventVec.add(createdEvent);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error creating event: " + e.getMessage());
        }

        return ResponseEntity.ok(eventVec);
    }

    @CrossOrigin
    @GetMapping("/")
	public ResponseEntity<?> getAllEvents(){
		
		
		try {
			List<EventModel> allEvents = this.eventService.getAllEvents();
			eventVec = new Vector<>(allEvents);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			
		}

		return ResponseEntity.ok(eventVec);
	}
}

