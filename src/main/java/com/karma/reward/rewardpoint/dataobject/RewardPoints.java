package com.karma.reward.rewardpoint.dataobject;

public class RewardPoints {

	private String customerId;
	private String customerName;
	private Integer rewardPoints;

//	public RewardPoints(String customerId, String customerName, Integer rewardPoints) {
//		this.customerId = customerId;
//		this.customerName = customerName;
//		this.rewardPoints = rewardPoints;
//	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Integer getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
