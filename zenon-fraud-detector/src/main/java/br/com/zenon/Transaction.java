package br.com.zenon;

import java.math.BigDecimal;

public record Transaction(int step, TransactionType type, BigDecimal amount, TransactionalCustomer origin,
                          TransactionalCustomer destination, boolean isFraud, boolean isFlaggedFraud) {

}
