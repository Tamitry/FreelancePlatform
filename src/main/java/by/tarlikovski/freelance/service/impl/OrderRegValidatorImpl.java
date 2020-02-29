package by.tarlikovski.freelance.service.impl;

import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.Validator;

import java.sql.Timestamp;

public class OrderRegValidatorImpl
        extends ServiceImpl implements Validator<Order> {
    @Override
    public boolean validate(final Order order)
            throws ServiceException {
        if (order.getOrderDeadLine().compareTo(new Timestamp(System.currentTimeMillis())) <= 0) {
            throw new ServiceException("wrongdateformat");
        }
        return true;
    }
}
