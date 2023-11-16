package com.route.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.route.config.ResourceNotFoundException;
import com.route.entities.RouteEntry;
import com.route.repo.RouteRepo;

@Service
public class RouteServiceImpl implements RouteServices{
	
	@Autowired
	private RouteRepo repo;

	@Override
	public RouteEntry saveRoute(RouteEntry route) {
		RouteEntry routeSaved = this.repo.save(route);
		return routeSaved;
	}

	@Override
	public List<RouteEntry> getAllRoute() {
		List<RouteEntry> routes = new ArrayList<>();
		routes = this.repo.findAll();
		return routes;
	}

	@Override
	public RouteEntry getRouteById(int id) {
		RouteEntry route = this.repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("route","id",id));
		return route;
	}

	@Override
	public RouteEntry updateRoute(int id, RouteEntry route) {
		RouteEntry getRoute = this.repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("route","id",id));
		getRoute.setCost(route.getCost());
		getRoute.setEditBy(route.getEditBy());
		getRoute.setEditOn(route.getEditOn());
		getRoute.setGroupId(route.getGroupId());
		getRoute.setNetworkId(route.getNetworkId());
		getRoute.setRemarks(route.getRemarks());
		getRoute.setSmscId(route.getSmscId());
		getRoute.setSmscType(route.getSmscType());
		getRoute.setUserId(route.getUserId());
		RouteEntry update = this.repo.save(getRoute);
		return update;
	}

	@Override
	public void deleteRouteById(int id) {
		RouteEntry getRoute = this.repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("route","id",id));
		this.repo.delete(getRoute);
	}

	


}
