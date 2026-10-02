package br.com.zenon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionIngestor {

    public List<Transaction> readFile(String fileName){
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            List<Transaction> lines = new ArrayList<>();
            String line;
            String[] chunks = null;
            int cont = 0;

            while ((line = br.readLine()) != null && cont < 1001) {
                if (cont != 0) {
                    chunks = line.split(",");

                    int step = Integer.parseInt(chunks[0]);
                    TransactionType type = TransactionType.valueOf(chunks[1]);
                    BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(chunks[2]));

                    var origin = new TransactionalCustomer(chunks[3], BigDecimal.valueOf(Double.parseDouble(chunks[4])), BigDecimal.valueOf(Double.parseDouble(chunks[5])));
                    var destination = new TransactionalCustomer(chunks[6], BigDecimal.valueOf(Double.parseDouble(chunks[7])), BigDecimal.valueOf(Double.parseDouble(chunks[8])));
                    boolean isFraud = "1".equals(chunks[9]);
                    boolean isFlaggedFraud = "1".equals(chunks[10]);
                    var transaction = new Transaction(step, type, amount, origin, destination, isFraud, isFlaggedFraud);

                    lines.add(transaction);
                }
                cont++;
            }
            IO.println("Total de linhas lidas: " + lines.size() );
             return lines;

        } catch (IOException e) {
            throw  new RuntimeException("Erro ao ler o arquivo: ");
        }

    }

}
