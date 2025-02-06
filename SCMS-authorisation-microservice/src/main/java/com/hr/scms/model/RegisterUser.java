package com.hr.scms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_info")
public class RegisterUser {

	@Id()
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	
}
