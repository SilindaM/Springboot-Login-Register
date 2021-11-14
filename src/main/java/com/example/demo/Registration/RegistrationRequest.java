package com.example.demo.Registration;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor

public class RegistrationRequest {
	private final String firstname;
	private final String lastname;
	private final String email;
	private final String password;

}
