package com.route.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.route.dto.RouteDTO;
import com.route.entities.RouteEntry;
import com.route.services.RouteServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/test")
@Tag(name="RouteController", description="API's for Routes")
public class RouteController {
	
	@Autowired
	private RouteServices service;
	
	private RouteEntry convertToEntity(RouteDTO route) {
		RouteEntry routeEntity = new RouteEntry();
		routeEntity.setCost(route.getCost());
		routeEntity.setEditBy(route.getEditBy());
		routeEntity.setEditOn(route.getEditOn());
		routeEntity.setGroupId(route.getGroupId());
		routeEntity.setNetworkId(route.getNetworkId());
		routeEntity.setRemarks(route.getRemarks());
		routeEntity.setSmscId(route.getSmscId());
		routeEntity.setSmscType(route.getSmscType());
		routeEntity.setUserId(route.getUserId());
		return routeEntity;
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/save/route")
	@Operation(summary="save all routes", description="post the routes")
	public ResponseEntity<RouteEntry> postRoute(@Valid @RequestBody RouteDTO route){
		RouteEntry re = convertToEntity(route);
		RouteEntry routeSave = this.service.saveRoute(re);
		return ResponseEntity.status(HttpStatus.CREATED).body(routeSave);
	} 
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/update/route/{id}")
	@Operation(summary="update routes by id",description="update the route by id")
	public ResponseEntity<RouteEntry> updateRoute(@Parameter(description="Give id to update route")@PathVariable("id") int id,@Valid @RequestBody RouteDTO route){
		RouteEntry re = convertToEntity(route);
		RouteEntry updatedRoute = this.service.updateRoute(id, re);
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedRoute);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/get/route/{id}")
	@Operation(summary="get route by id",description="get the route by id")
	public ResponseEntity<RouteEntry> getRouteById(@Parameter(description="Give id to get route") @PathVariable("id") int id){
		return ResponseEntity.ok(this.service.getRouteById(id));
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/delete/route/{id}")
	@Operation(summary="delete route by id", description="Delete the route by id")
	public ResponseEntity<String> deleteRouteById(@Parameter(description="Give id to delete route")@PathVariable("id") int id){
		this.service.deleteRouteById(id);
		return new ResponseEntity<>("Route deleted successfully.",HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/get-all-routes")
	@Operation(summary="get all routes",description="get all the routes")
	public ResponseEntity<?> getAllRoutes(){
		List<RouteEntry> allRoutes = new ArrayList<>();
		allRoutes = this.service.getAllRoute();
		return ResponseEntity.ok(allRoutes);
	}
	

}
