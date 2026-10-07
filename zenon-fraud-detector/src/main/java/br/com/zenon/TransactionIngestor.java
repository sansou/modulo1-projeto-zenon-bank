package br.com.zenon;

import java.io.IOException;
import java.math.BigDecimal;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;
import java.util.Optional;


public class TransactionIngestor {
    public static final int FRAUD_LIMIT = 50_000;

    public List<Transaction> readFile(String fileName) {
        Path path = Path.of(fileName);
        try {
            List<String> lines = Files.readAllLines(path);
            return lines.stream()
                    .skip(1)
                    .limit(FRAUD_LIMIT)
                    .map(this::parseTransaction)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();

        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo: ");
        }
    }

    private Optional<Transaction> parseTransaction(String line) {
        try {
            String[] chunks = line.split(",");

            int step = Integer.parseInt(chunks[0]);
            TransactionType type = TransactionType.valueOf(chunks[1]);

            BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(chunks[2]));

            var origin = new TransactionalCustomer(chunks[3], BigDecimal.valueOf(Double.parseDouble(chunks[4])), BigDecimal.valueOf(Double.parseDouble(chunks[5])));
            var destination = new TransactionalCustomer(chunks[6], BigDecimal.valueOf(Double.parseDouble(chunks[7])), BigDecimal.valueOf(Double.parseDouble(chunks[8])));

            boolean isFraud = "1".equals(chunks[9]);
            boolean isFlaggedFraud = "1".equals(chunks[10]);

            return Optional.of(new Transaction(step, type, amount, origin, destination, isFraud, isFlaggedFraud));
        } catch (Exception e) {
            System.err.println("Erro ao fazer parse: " + line + " | " + e.getMessage());
            return Optional.empty();
        }
    }

}
