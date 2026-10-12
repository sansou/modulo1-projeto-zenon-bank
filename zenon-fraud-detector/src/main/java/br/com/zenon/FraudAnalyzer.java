package br.com.zenon;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class FraudAnalyzer {

    private final List<Transaction> transactions;

    public FraudAnalyzer(List<Transaction> transactions){
        Objects.requireNonNull(transactions);
        this.transactions = transactions;
    }

    public long countFrauds() {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .count();
    }

    public List<BigDecimal> findhiggestsFrauds(int quantidade) {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .map(Transaction::amount)
                .limit(quantidade).toList();
    }

    public List<String> maioresClientesSuspeitos(int quantidade) {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .map(t -> t.origin().name())
                .distinct()
                .limit(quantidade).toList();

    }

    public BigDecimal totalFraudAmount(){
        return transactions.stream()
                .filter(Transaction::isFraud)
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<TransactionType, Long> aggregateFraudulentTransactions() {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .collect(Collectors.groupingBy(Transaction::type, Collectors.counting()));
    }
}
