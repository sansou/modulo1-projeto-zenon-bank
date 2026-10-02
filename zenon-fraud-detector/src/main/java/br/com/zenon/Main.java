package br.com.zenon;

import java.math.BigDecimal;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        IO.println();
        IO.println("******************************* Records - Início ****************************************************");
        IO.println();

        var origin1 = new TransactionalCustomer("C1231006815",new BigDecimal("170136.0"), new BigDecimal("160296.36"));
        var destination1 = new TransactionalCustomer("M1979787155",new BigDecimal("0.0"), new BigDecimal("0.0"));
        var t1 = new Transaction(1, TransactionType.PAYMENT, new BigDecimal("9839.64"), origin1, destination1,  false, false);


        var origin2 = new TransactionalCustomer("C1280323807",new BigDecimal("850002.52"), new BigDecimal("0.0"));
        var destination2 = new TransactionalCustomer("C873221189",new BigDecimal("6510099.11"), new BigDecimal("7360101.63"));
        var t2 = new Transaction(743, TransactionType.CASH_OUT, new BigDecimal("850002.52"), origin2, destination2, true, false);

        IO.println("Transação 1:");
        IO.println(t1.toString() +"\n");

        IO.println("Transação 2: ");
        IO.println(t2.toString());

        IO.println();
        IO.println("******************************* Ingeastão de dados  - Fim ****************************************************");
        IO.println();

        IO.println();
        IO.println("******************************* Ingestão de dados - Início ****************************************************");
        IO.println();
        var transactionIngestor = new TransactionIngestor();
        List<Transaction> transactions = transactionIngestor.readFile("data/PS_20174392719_1491204439457_log.csv");

        IO.println();
        transactions.stream().limit(10).forEach(IO::println);

        IO.println();
        IO.println("******************************* Ingeastão de dados  - Fim ****************************************************");
        IO.println();


    }
}
