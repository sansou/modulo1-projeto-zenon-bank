package br.com.zenon;

import java.math.BigDecimal;
import java.util.List;

public class DBMain {
    void main(){

        ConnectionFactory.getConnection();
        IO.println("Conectado ao banco de dados");

        var repository = new TransactionSQLRepository();

//        repository.findByOriginName("C1000001")
//                .ifPresentOrElse(IO::println,() -> IO.println("transação não encontrada para: C1000001 ") );
//        repository.findByOriginName("C12341")
//                .ifPresentOrElse(IO::println,() -> IO.println("transação não encontrada para: C12341 ") );

//        var origin1 = new TransactionalCustomer("C1231006815",new BigDecimal("170136.0"), new BigDecimal("160296.36"));
//        var destination1 = new TransactionalCustomer("M1979787155",new BigDecimal("0.0"), new BigDecimal("0.0"));
//        var t1 = new Transaction(1, TransactionType.PAYMENT, new BigDecimal("9839.64"), origin1, destination1,false, false);
//
//        repository.save(t1);

//        var transactionIngestor = new TransactionIngestor();
//
//        long startTime = System.nanoTime();
//        List<Transaction> transactions =transactionIngestor.readFile("data/PS_20174392719_1491204439457_log.csv");
//
//        IO.println("Iniciando adiçoes no banco de dados");
//        transactions.forEach(repository::save);
//
//        long endTime = System.nanoTime();
//
//        IO.println("Tempo total para 100.000 = " + (endTime - startTime)/ 1_000_000.0 + " ms");


        var transactionResult1 = repository.findByOriginName("C1231006815");
        transactionResult1.ifPresentOrElse(IO::println, () -> IO.println("Transação nao encontrada para: C1231006815"));

        var transactionResult2 = repository.findByOriginName("C12345");
        transactionResult2.ifPresentOrElse(IO::println, () -> IO.println("Transação nao encontrada para: C12345"));
    }
}
