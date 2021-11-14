 package com.example.demo.Appuser;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Registration.Token.ConfirmationToken;
import com.example.demo.Registration.Token.ConfirmationTokenService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Appuserservice implements UserDetailsService {

	private final AppUserRepository appUserRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ConfirmationTokenService confirmationTokenService;
	
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return appUserRepository.findByEmail(email).
				orElseThrow(()->new UsernameNotFoundException(String.format("username not found", email)));
	}
	public String signupUser(Appuser appuser) {
		boolean userExists=appUserRepository.findByEmail(appuser.getEmail()).isPresent();
		if(userExists) {
			throw new IllegalStateException("Email already taken");
		}
		String encodedPassword=bCryptPasswordEncoder.encode(appuser.getPassword());
		appuser.setPassword(encodedPassword);
		appUserRepository.save(appuser);
		//send confirmation  token
		String token=UUID.randomUUID().toString();
		ConfirmationToken confirmationToken=new ConfirmationToken(
				token,
				LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15),
				appuser
				);
		confirmationTokenService.saveConfirmationToken(confirmationToken);
		 
		return token;
	} 
	public int enableAppUser(String email) {
        return appUserRepository.enableAppuser(email);
    }
}
