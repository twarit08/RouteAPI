package com.route.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.route.entities.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer>{

}
