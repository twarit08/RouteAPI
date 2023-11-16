package com.route.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.route.entities.RouteEntry;

@Repository
public interface RouteRepo extends JpaRepository<RouteEntry, Integer>{

}
