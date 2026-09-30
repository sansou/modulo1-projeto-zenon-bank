package br.com.zenon;

import java.math.BigDecimal;

public record TransactionalCustomer (String name, BigDecimal oldBalance, BigDecimal newBalance) {

}