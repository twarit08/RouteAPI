package com.route.entities;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "routemaster")
@Schema(description = "RouteEntry entity")
public class RouteEntry implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private int id;
	
	@Column(name = "user_id", nullable = false, updatable = false)
	@Min(value=1)
	@NotNull
	private int userId;
	
	@Column(name = "network_id", nullable = false, updatable = false)
	@Min(value=1)
	@NotNull
	private int networkId;
	
	@Column(name = "smsc_id")
	@Min(value=1)
	@NotNull
	private int smscId;
	
	@Column(name = "group_id")
	@Min(value=1)
	@NotNull
	private int groupId;
	
	@Column(name = "cost")
	@Min(value=1)
	@NotNull
	private double cost;
	
	@Column(name = "smsc_type")
	@NotBlank(message = "smsc_type can't be blank")
	private String smscType;
	
	@Column(name = "editby")
	@NotBlank(message = "editby can't be blank")
	private String editBy;
	
	@Column(name = "edit_on")
	@NotBlank(message = "edit_on can't be blank")
	private String editOn;
	
	@Column(name = "remarks")
	@NotBlank(message = "remarks can't be blank")
	private String remarks;
	
	public RouteEntry() {
	}

	public RouteEntry(int userId, String editBy, String editOn, String remarks) {
		this.userId = userId;
		this.editBy = editBy;
		this.editOn = editOn;
		this.remarks = remarks;
	}

	public RouteEntry(int userId, int networkId, int smscId, int groupId, double cost, String smscType, String editBy,
			String editOn, String remarks) {
		this.userId = userId;
		this.networkId = networkId;
		this.smscId = smscId;
		this.cost = cost;
		this.smscType = smscType;
		this.editBy = editBy;
		this.editOn = editOn;
		this.groupId = groupId;
		this.remarks = remarks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getNetworkId() {
		return networkId;
	}

	public void setNetworkId(int networkId) {
		this.networkId = networkId;
	}

	public int getSmscId() {
		return smscId;
	}

	public void setSmscId(int smscId) {
		this.smscId = smscId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getSmscType() {
		return smscType;
	}

	public void setSmscType(String smscType) {
		this.smscType = smscType;
	}

	public String getEditBy() {
		return editBy;
	}

	public void setEditBy(String editBy) {
		this.editBy = editBy;
	}

	public String getEditOn() {
		return editOn;
	}

	public void setEditOn(String editOn) {
		this.editOn = editOn;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String toString() {
		return "Route: id=" + id + ",userId=" + userId + ",networkId=" + networkId + ",smscId=" + smscId + ",groupId="
				+ groupId + ",Type=" + smscType + ",Cost=" + cost + ",remarks=" + remarks;
	}
	

}
