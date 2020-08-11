package org.com.dao;

import java.text.SimpleDateFormat;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class BankTransactionIItemProcessor implements ItemProcessor<BankTransaction, BankTransaction>{

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
	@Override
	public BankTransaction process(BankTransaction item) throws Exception {
		item.setTransactionDate(dateFormat.parse(item.getStrTransationDate()));

		return item;
	}

}
