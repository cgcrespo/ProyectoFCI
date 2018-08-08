package com.ejemplo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ejemplo.model.User;

public interface UserRepository extends MongoRepository<User, String>
{
	 public User findOneByName(String name);
}
