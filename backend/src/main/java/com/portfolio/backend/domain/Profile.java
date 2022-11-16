package com.portfolio.backend.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="users") // this should create the table
public class Profile {

	// unique identifier
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	// basic account details
	public String username = "";
	public String password = "";
	public String email = "";
	public String phone = "";
	
	// specific account details
	
	// storing currency as float or double results in accounting errors.
	// instead I'm storing the value in pennies. 
	public Long sumDonationsGiven = 0L;
	public Long sumDonationsRecieved = 0L;
	
	// if we tried to store all posts/comments/... in a profile then it would quickly become unmanageable.
	public Long firstPostID = 0L;
	public Long firstCommentID = 0L;
	public Long firstDonationID = 0L;
	
	
	public Profile() {}
}
