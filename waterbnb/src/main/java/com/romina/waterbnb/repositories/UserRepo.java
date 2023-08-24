package com.romina.waterbnb.repositories;

import org.springframework.data.repository.CrudRepository;

import com.romina.waterbnb.models.User;

public interface UserRepo extends CrudRepository<User, Long> {
	
	User findByEmail(String email);
	

}
