package com.example.occ.event;


import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.example.occ.user.UserModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Event")
public class EventModel {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int eventId;
	
	@NotBlank(message="title should not be Null")
	@Column(unique= true, nullable=false)
	private String title;
	
	@Column(nullable=false)
	@NotBlank(message="description should not be null")
	private String description;

	@Column(nullable=false)
	@NotBlank(message="venue should not be null")
	private String venue;

	@Column(nullable=false)
	@NotBlank(message="fees should not be null")
	private String fees;

	@Column(nullable=false)
	@NotBlank(message="Start time should not be null")
	private String start_time;
	
	@Column(nullable=false)
	@NotBlank(message="End time should not be null")
	private String end_time;

	@Column(nullable=false)
	@NotBlank(message="start date should not be null")
	private Date start_date;
	@Column(nullable=false)
	@NotBlank(message="End date should not be null")
	private Date end_date;

	@Lob
	@Column(columnDefinition = "BLOB")
	private byte[] image;
	
	
	
	
	@CreationTimestamp
	@Column(nullable= false, updatable= false)
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	
	 
	

}