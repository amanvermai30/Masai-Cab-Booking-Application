package com.masai.cabbooking.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull(message = "date can't set as Null")
	private LocalDate reservationDate;

	@NotNull(message = "source can't set as Null")
	private String source;

	@NotNull(message = "destination can't set as Null")
	private String destination;

	@ManyToOne
	private Cab cab;

	@ManyToOne
	private USER user;

}
