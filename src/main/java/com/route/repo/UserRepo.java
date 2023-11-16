package com.route.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.route.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	public User findByUsername(String username);

}
