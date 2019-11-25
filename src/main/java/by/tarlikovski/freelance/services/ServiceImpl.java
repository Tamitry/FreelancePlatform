package by.tarlikovski.freelance.services;

import by.tarlikovski.freelance.dao.Transaction;

public class ServiceImpl implements Service {
    private Transaction transaction = null;

    public void setTransaction(final Transaction trans) {
        transaction = trans;
    }

    protected Transaction getTransaction() {
        return transaction;
    }
}
