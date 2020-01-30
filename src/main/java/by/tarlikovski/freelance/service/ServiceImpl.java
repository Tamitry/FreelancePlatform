package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.dao.transaction.Transaction;

abstract public class ServiceImpl implements Service {
    protected Transaction transaction = null;

    public void setTransaction(final Transaction transaction) {
        this.transaction = transaction;
    }
}
