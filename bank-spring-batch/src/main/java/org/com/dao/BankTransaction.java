package org.com.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class BankTransaction {

	@Id
	private Long id ;
	private Long account_id ;
	private Date transactionDate;
	@Transient
	private String strTransationDate;
	private String transactionType ;
	private double amount;




}
