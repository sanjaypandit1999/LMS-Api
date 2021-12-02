package com.bridgelabz.lmsapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.lmsapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "select * from employeepayroll_db where email= :email", nativeQuery = true)
	Optional<User> findByEmail(String email);

}
