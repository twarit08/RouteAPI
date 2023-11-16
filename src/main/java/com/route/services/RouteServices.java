package com.route.services;

import java.util.List;

import com.route.entities.RouteEntry;

public interface RouteServices {
	
	public RouteEntry saveRoute(RouteEntry route);
	public List<RouteEntry> getAllRoute();
	public RouteEntry getRouteById(int id);
	public RouteEntry updateRoute(int id,RouteEntry route);
	public void deleteRouteById(int id);

}
