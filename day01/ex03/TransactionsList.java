package ex03;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction instance);
    void removeTransactionById(UUID id);
    Transaction [] toArray();
}
