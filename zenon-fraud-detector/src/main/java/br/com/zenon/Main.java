package br.com.zenon;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

//        IO.println();
//        IO.println("******************************* Records - Início ****************************************************");
//        IO.println();
//
//        var origin1 = new TransactionalCustomer("C1231006815",new BigDecimal("170136.0"), new BigDecimal("160296.36"));
//        var destination1 = new TransactionalCustomer("M1979787155",new BigDecimal("0.0"), new BigDecimal("0.0"));
//        var t1 = new Transaction(1, TransactionType.PAYMENT, new BigDecimal("9839.64"), origin1, destination1,  false, false);
//
//
//        var origin2 = new TransactionalCustomer("C1280323807",new BigDecimal("850002.52"), new BigDecimal("0.0"));
//        var destination2 = new TransactionalCustomer("C873221189",new BigDecimal("6510099.11"), new BigDecimal("7360101.63"));
//        var t2 = new Transaction(743, TransactionType.CASH_OUT, new BigDecimal("850002.52"), origin2, destination2, true, false);
//
//        IO.println("Transação 1:");
//        IO.println(t1.toString() +"\n");
//
//        IO.println("Transação 2: ");
//        IO.println(t2.toString());
//
//        IO.println();
//        IO.println("******************************* Records  - Fim ****************************************************");
//        IO.println();
//
//        IO.println();
//        IO.println("******************************* Ingestão de dados - Início ****************************************************");
//        IO.println();
        var transactionIngestor = new TransactionIngestor();
//        List<Transaction> transactions = transactionIngestor.readFile("data/PS_20174392719_1491204439457_log.csv");
//
//        IO.println();
//        transactions.stream().limit(10).forEach(IO::println);
//
//        IO.println();
//        IO.println("******************************* Ingestão de dados  - Fim ****************************************************");
//        IO.println();
//
//
//        IO.println();
//        IO.println("******************************* Tratamento de Erros- Início ****************************************************");
//        IO.println();
//
//        List<Transaction> transactionsBadData = transactionIngestor.readFile("data/paysim_with_bad_data.csv");
//        IO.println(transactionsBadData.size());
//
//        transactionsBadData.forEach(IO::println);
//
//        IO.println();
//        IO.println("******************************* Tratamento de Erros  - Fim ****************************************************");
//        IO.println();


//        IO.println();
//        IO.println("******************************* Stream - Início ****************************************************");
//        IO.println();
//
//        List<Transaction> transactions = transactionIngestor.readFile("data/PS_20174392719_1491204439457_log.csv");
//        var fraudAnalyzer = new FraudAnalyzer(transactions);
//
//        long totalFraudes = fraudAnalyzer.contarFrauds();
//        IO.println("Total de fraudes: " + totalFraudes);
//
//        IO.println("Top 3 maiores fraudes: ");
//        List<BigDecimal> maioresFraudes = fraudAnalyzer.buscarAsMaioresFraudes(3);
//        maioresFraudes.forEach(amount -> IO.println("- %.2f".formatted(amount)));
//
//        IO.println("Top 5 clientes suspeitos: ");
//        List<String> suspeitos = fraudAnalyzer.maioresClientesSuspeitos(5);
//        suspeitos.forEach(IO::println);
//
//        BigDecimal totalPerdidoFraudes = fraudAnalyzer.calcularTotalFraudes();
//        IO.println("Total de fraudes: " + totalPerdidoFraudes );
//
//        Map<TransactionType, Long> contagemFraudPorTipo = fraudAnalyzer.ContagemFraudPorTipo();
//        IO.println("Contagem de fraudes por tipo: ");
//        contagemFraudPorTipo.forEach((k,v) -> IO.println(k + " : " + v));
//
//        IO.println();
//        IO.println("******************************* Stream - Fim ****************************************************");
//        IO.println();

        IO.println();
        IO.println("******************************* Benchmark - Início ****************************************************");
        IO.println();

        List<Transaction> transactions = transactionIngestor.readFile("data/PS_20174392719_1491204439457_log.csv");
        String nameClient = "C1868032458";

        TransactionRepository transactionRepository;

        transactionRepository = new TransactionListRepository(transactions);

        long startTimeList = System.nanoTime();
        transactionRepository.findByOriginName(nameClient).ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para: " + nameClient));
        long endTimeList = System.nanoTime();

        IO.println("Tempo de busca - LISTA (ms) = " + (endTimeList - startTimeList)/ 1_000_000.0);

        transactionRepository = new TransactionMapRepository(transactions);

        long startTimeMap = System.nanoTime();
        transactionRepository.findByOriginName(nameClient).ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para: " + nameClient));
        long endTimeMap = System.nanoTime();

        IO.println("Tempo de busca - MAP (ms) = " + (endTimeMap - startTimeMap)/ 1_000_000.0);


        IO.println();
        IO.println("******************************* Benchmark - Fim ****************************************************");
        IO.println();



    }
}
