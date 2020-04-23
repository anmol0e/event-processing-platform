package com.karma.reward.rewardpoint.spark;

import com.karma.reward.rewardpoint.dataobject.Transaction;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Row;

public class RowMapper implements MapFunction<Row, Transaction> {

	@Override
	public Transaction call(Row row) {
		Transaction transaction = new Transaction();
		transaction.setCustomerId(row.getAs("Customer ID"));
		transaction.setCustomerName(row.getAs("Customer Name"));
		transaction.setBankName(row.getAs("Bank Name"));
		transaction.setTransactionAmount(row.getAs("Transaction Amount (EUR)"));
		transaction.setMerchantType(row.getAs("Merchant Type"));
		transaction.setTransactionType(row.getAs("Transaction Type"));
		return transaction;
	}
}
