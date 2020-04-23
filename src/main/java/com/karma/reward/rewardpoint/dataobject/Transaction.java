package com.karma.reward.rewardpoint.dataobject;

public class Transaction {

	private String customerId;
	private String customerName;
	private String bankName;
	private Double transactionAmount;
	private String merchantType;
	private String transactionType;
	private Double rewardPoints;

	public Transaction() {
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Double getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(Double rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
}
