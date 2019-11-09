package exercise.customeraccount.transaction.repository;

import exercise.customeraccount.repository.DefaultRepository;
import exercise.customeraccount.transaction.model.Transaction;

public interface TransactionRepository extends DefaultRepository<Transaction,String> {
}
