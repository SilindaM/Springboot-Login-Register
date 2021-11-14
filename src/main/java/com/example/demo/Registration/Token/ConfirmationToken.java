package com.example.demo.Registration.Token;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.Appuser.Appuser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String token;
	@Column(nullable = false)
	private LocalDateTime created;
	@Column(nullable = false)
	private LocalDateTime expired;
	
	private LocalDateTime confirmed;
	@ManyToOne
	@JoinColumn(nullable = false,name="app_user_id")
	private Appuser appuser;
	
	public ConfirmationToken(String token, LocalDateTime created, LocalDateTime expired, Appuser appuser) {
	
		this.token = token;
		this.created = created;
		this.expired = expired;
		this.appuser=appuser;
	}
	
	
}
