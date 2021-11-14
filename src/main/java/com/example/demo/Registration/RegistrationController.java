package com.example.demo.Registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("api/registration/")
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationController {
	@Autowired
	private RegistrationService registrationService;

	@PostMapping
	public String register(@RequestBody RegistrationRequest registrationrequest) {
		
		return registrationService.register(registrationrequest);
	}
	
}
