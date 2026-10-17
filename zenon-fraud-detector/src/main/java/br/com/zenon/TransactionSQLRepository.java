package br.com.zenon;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TransactionSQLRepository implements TransactionRepository {
    @Override
    public Optional<Transaction> findByOriginName(String originName) {
        String sql = """
                SELECT id, step,amount, `type`,
                       name_origin, old_balance_origin, new_balance_origin,
                       name_recipient, old_balance_recipient, new_balance_recipiente,
                       is_fraud, is_flagged_fraud
                FROM zenon_frauds.transactions
                WHERE name_origin = ?
                LIMIT 1
                """;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, originName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Transaction transaction = mapResultSetToTransaction(rs);
                    return Optional.of(transaction);

                } else {
                    return Optional.empty();
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar a transação de "+ originName, e);
        }

    }

    @Override
    public void save(Transaction transaction) {
        String sql = """
                insert INTO transactions
                (step, `type`, amount, name_origin,old_balance_origin, new_balance_origin, name_recipient, old_balance_recipient, new_balance_recipiente, is_fraud, is_flagged_fraud)
                VALUES (?,?,?,?,?,?,?,?,?,?,?);
                """;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, transaction.step());
            ps.setString(2, transaction.type().name());
            ps.setBigDecimal(3, transaction.amount());

            ps.setString(4, transaction.origin().name());
            ps.setBigDecimal(5, transaction.origin().oldBalance());
            ps.setBigDecimal(6, transaction.origin().newBalance());

            ps.setString(7, transaction.destination().name());
            ps.setBigDecimal(8, transaction.destination().oldBalance());
            ps.setBigDecimal(9, transaction.destination().newBalance());

            ps.setBoolean(10, transaction.isFraud());
            ps.setBoolean(11, transaction.isFlaggedFraud());

            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar a transação", e);
        }
    }

    private Transaction mapResultSetToTransaction(ResultSet rs) {
        try {
            int step = rs.getInt("step");
            TransactionType type = TransactionType.valueOf(rs.getString("type"));
            BigDecimal amount = rs.getBigDecimal("amount");

            TransactionalCustomer origin = new TransactionalCustomer(rs.getString("name_origin"), rs.getBigDecimal("old_balance_origin"), rs.getBigDecimal("new_balance_origin"));
            TransactionalCustomer destination = new TransactionalCustomer(rs.getString("name_recipient"), rs.getBigDecimal("old_balance_recipient"), rs.getBigDecimal("new_balance_recipiente"));

            boolean isFraud = rs.getBoolean("is_fraud");
            boolean isFlaggedFraud = rs.getBoolean("is_flagged_fraud");

            return new Transaction(step, type, amount, origin, destination, isFraud, isFlaggedFraud);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
