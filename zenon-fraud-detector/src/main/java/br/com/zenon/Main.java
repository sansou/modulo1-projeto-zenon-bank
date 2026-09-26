package br.com.zenon;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        var t1 = new Transaction(1, TransactionType.PAYMENT, 9839.64, "C1231006815", 170136.0, 160296.36, "M1979787155", 0.0, 0.0, false, false);

        var t2 = new Transaction(743, TransactionType.CASH_OUT, 850002.52, "C1280323807", 850002.52, 0.0, "C873221189", 6510099.11, 7360101.63, true, false);

        IO.println("\t Transação 1");
        IO.println(t1.toString() +"\n");

        IO.println("\t Transação 2");
        IO.println(t2.toString());
    }
}
