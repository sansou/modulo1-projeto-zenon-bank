package br.com.zenon;


public class ReportMain {
     void main() {
         TransactionReport transactionReport = new TransactionReport();
         TransactionReport.Statistics statistics = transactionReport.generatReport("data/PS_20174392719_1491204439457_log.csv");
         IO.println("Total de linhas lidas: " + statistics.totalTransactions());
         IO.println("Total de fraudes: " + statistics.totalFrauds());
         IO.println("Valor total transacionado: " + statistics.totalAmount() );
     }
}
