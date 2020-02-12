package by.tarlikovski.freelance.service.impl;

import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.dao.transaction.Impl.TransactionFactoryImpl;
import by.tarlikovski.freelance.dao.transaction.TransactionFactory;
import by.tarlikovski.freelance.dao.DAOException;
import by.tarlikovski.freelance.service.*;

public class ServiceFactoryImpl implements ServiceFactory {
    private TransactionFactory transactionFactory;

    public ServiceFactoryImpl() throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Service getService(final ServiceName type) throws ServiceException {
        try {
            switch (type) {
                case USER_SERVICE:
                    UserServiceImpl userService = new UserServiceImpl();
                    userService.setTransaction(transactionFactory.createTransaction());
                    return userService;
                case SKILL_SERVICE:
                    SkillServiceImpl skillService = new SkillServiceImpl();
                    skillService.setTransaction(transactionFactory.createTransaction());
                    return skillService;
                case ORDER_PROPERTY_SERVICE:
                    OrderPropertyServiceImpl ordPropServ = new OrderPropertyServiceImpl();
                    ordPropServ.setTransaction(transactionFactory.createTransaction());
                    return ordPropServ;
                case WORK_SERVICE:
                    WorkServiceImpl workService = new WorkServiceImpl();
                    workService.setTransaction(transactionFactory.createTransaction());
                    return workService;
                case ORDER_SERVICE:
                    OrderServiceImpl orderService = new OrderServiceImpl();
                    orderService.setTransaction(transactionFactory.createTransaction());
                    return orderService;
                case CATEGORY_SERVICE:
                    CategoryServiceImpl categoryService = new CategoryServiceImpl();
                    categoryService.setTransaction(transactionFactory.createTransaction());
                    return categoryService;
                default:
                    throw new ServiceException("Service is not exist.");
            }
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    public static void init() throws ServiceException {
        try {
            TransactionFactoryImpl.init();
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void close() throws ServiceException {
        try {
            transactionFactory.close();
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
