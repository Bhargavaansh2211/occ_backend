package com.example.occ.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmailController {

	@Autowired private EmailService emailService;

	@CrossOrigin
	@PostMapping("/sendMail")
	public String
	sendMail(@RequestBody EmailDetails mail)
	{
		String status
			= emailService.sendMail(mail);

		return status;
	}

	
}
