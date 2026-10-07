package br.com.zenon;

import java.math.BigDecimal;
import java.util.Objects;

public record TransactionalCustomer (String name, BigDecimal oldBalance, BigDecimal newBalance) {
    public TransactionalCustomer{
        Objects.requireNonNull(name);
        Objects.requireNonNull(oldBalance);
        Objects.requireNonNull(newBalance);

        if (oldBalance.signum() < 0) throw new IllegalArgumentException("O valor de oldBalance deve ser maior ou igual a zero: "+ oldBalance);
        if (newBalance.signum() < 0) throw new IllegalArgumentException("O valor de newBalance deve ser maior ou igual a zero: "+ newBalance);
    }
}