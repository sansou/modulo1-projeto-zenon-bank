package br.com.zenon;

public record Transaction(int step, TransactionType type, double amount, String nameOrig, double oldbalanceOrg, double newbalanceOrig, String nameDest, double oldbalanceDest, double newbalanceDest, boolean isFraud, boolean isFlaggedFraud) {
}
