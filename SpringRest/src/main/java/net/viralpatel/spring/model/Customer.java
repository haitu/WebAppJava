package net.viralpatel.spring.model;

import java.util.Date;

public class Customer {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private Date dateOfBirth;

	public Customer(long id, String firstName, String lastName, String email, String mobile) {
		this.setId(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.dateOfBirth = new Date();
	}

	public Customer() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	//..Getter and setter methods

}
