package com.route.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "RouteDTO class")
public class RouteDTO {
	
	@Min(value=1)
	@NotNull
	@Schema(name="userId", accessMode = Schema.AccessMode.AUTO, description="user id of user")
	private int userId;
	
	@Min(value=1)
	@NotNull
	@Schema(name="networkId", accessMode = Schema.AccessMode.AUTO, description="network id details")
	private int networkId;
	
	@Min(value=1)
	@NotNull
	@Schema(name="smscId", accessMode = Schema.AccessMode.AUTO, description="smsc id details")
	private int smscId;
	
	@Min(value=1)
	@NotNull
	@Schema(name="groupId", accessMode = Schema.AccessMode.AUTO, description="group id details")
	private int groupId;

	@Min(value=1)
	@NotNull
	@Schema(name="cost", accessMode = Schema.AccessMode.AUTO, description="cost details")
	private double cost;
	
	@NotBlank(message = "smsc_type can't be blank")
	@Schema(name="smscType", accessMode = Schema.AccessMode.AUTO, description="smsc type details")
	private String smscType;
	
	@NotBlank(message = "editby can't be blank")
	@Schema(name="editBy", accessMode = Schema.AccessMode.AUTO, description="edit by details")
	private String editBy;
	
	@NotBlank(message = "edit_on can't be blank")
	@Schema(name="editOn", accessMode = Schema.AccessMode.AUTO, description="edit on details")
	private String editOn;
	
	@NotBlank(message = "remarks can't be blank")
	@Schema(name="remarks", accessMode = Schema.AccessMode.AUTO, description="remarks can be added")
	private String remarks;

	public RouteDTO(int userId, int networkId, int smscId, int groupId, double cost, String smscType,
			String editBy, String editOn, String remarks) {
		super();
		this.userId = userId;
		this.networkId = networkId;
		this.smscId = smscId;
		this.groupId = groupId;
		this.cost = cost;
		this.smscType = smscType;
		this.editBy = editBy;
		this.editOn = editOn;
		this.remarks = remarks;
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

	public RouteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
