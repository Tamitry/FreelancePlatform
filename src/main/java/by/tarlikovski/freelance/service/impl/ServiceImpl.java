package by.tarlikovski.freelance.service.impl;

import by.tarlikovski.freelance.dao.transaction.Transaction;
import by.tarlikovski.freelance.service.Service;

abstract public class ServiceImpl implements Service {
    protected Transaction transaction = null;

    public void setTransaction(final Transaction transaction) {
        this.transaction = transaction;
    }
}
