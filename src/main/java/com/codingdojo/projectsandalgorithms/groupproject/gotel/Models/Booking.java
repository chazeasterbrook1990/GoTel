package com.codingdojo.projectsandalgorithms.groupproject.gotel.Models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="bookings")

public class Booking {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty(message = "Please pick a state in the United States.")
	@NotNull(message = "This field cannot be blank or without characters.")
	private String state;
	
	@NotEmpty(message = "Please pick a city within the state selected.")
	@NotNull(message = "This field cannot be blank or without characters.")
	private String city;
	
	@NotEmpty(message = "Please enter a check in date.")
	@NotNull(message = "This field cannot be blank or without characters.")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date checkInDate;
	
	@NotEmpty(message = "Please enter a check out date.")
	@NotNull(message = "This field cannot be blank or without characters.")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date checkOutDate;
	
	@Column(updatable = false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	public Booking () {}

	public Booking(Long id,
			@NotEmpty(message = "Please pick a state in the United States.") @NotNull(message = "This field cannot be blank or without characters.") String state,
			@NotEmpty(message = "Please pick a city within the state selected.") @NotNull(message = "This field cannot be blank or without characters.") String city,
			@NotEmpty(message = "Please enter a check in date.") @NotNull(message = "This field cannot be blank or without characters.") Date checkInDate,
			@NotEmpty(message = "Please enter a check out date.") @NotNull(message = "This field cannot be blank or without characters.") Date checkOutDate,
			Date createdAt, Date updatedAt, User user) {
		super();
		this.id = id;
		this.state = state;
		this.city = city;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
