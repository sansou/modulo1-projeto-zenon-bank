package br.com.zenon;


import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

public class ReportMain {
    void main(String[] args) {
        String language = (args.length > 0 ? args[0]: "pt");
        var locale = Locale.of(language);

        TransactionReport transactionReport = new TransactionReport();
        TransactionReport.Statistics statistics = transactionReport.generatReport("data/PS_20174392719_1491204439457_log.csv");

        var integerFormatter = NumberFormat.getIntegerInstance(locale);
        var currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        currencyFormatter.setCurrency(Currency.getInstance("USD"));

        var resourcebundle = ResourceBundle.getBundle("report", locale);

        String fmtTotalTransactions = integerFormatter.format(statistics.totalTransactions());
        String fmtTotalAmount = currencyFormatter.format(statistics.totalAmount());
        String fmtTotalFrauds = integerFormatter.format(statistics.totalFrauds());

        String msgTotalTransaction = resourcebundle.getString("label.total.transactions");
        String msgTotalFrauds = resourcebundle.getString("label.total.frauds");
        String msgTotalAmount = resourcebundle.getString("label.total.amount");

        IO.println(msgTotalTransaction + fmtTotalTransactions);
        IO.println(msgTotalFrauds + fmtTotalFrauds);
        IO.println(msgTotalAmount + fmtTotalAmount);
    }
}
