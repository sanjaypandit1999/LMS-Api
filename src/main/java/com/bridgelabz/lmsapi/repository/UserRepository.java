package com.bridgelabz.lmsapi.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.bridgelabz.lmsapi.model.User;

/**
 * purpose to do all CRUD operation using mongoRepository
 * 
 * @author Sanjay
 * @version 1.0
 * @since 12/17/2021
 */
public interface UserRepository extends MongoRepository<User, Long> {
	/**
	 * purpose to find existing email in mongoDB by using @Query
	 * 
	 */
	@Query("{ 'email' : ?0 }")
	Optional<User> findByEmail(String email);

}
