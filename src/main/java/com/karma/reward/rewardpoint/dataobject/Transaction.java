package com.karma.reward.rewardpoint.dataobject;

import java.io.Serializable;

public class Transaction implements Serializable {

	private String customerId;
	private String customerName;
	private String bankName;
	private Double transactionAmount;
	private String merchantType;
	private String transactionType;
}
