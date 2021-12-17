package com.bridgelabz.lmsapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * purpose to generate auto increment id using database sequence field
 * 
 * @author Sanjay
 * @version 1.0
 * @since 12/17/2021
 */
@Document(collection = "database_sequences")
@Getter
@Setter
@Component
public class DatabaseSequence {

	   @Id
	    private String id;

	    private long seq;
	    
}
