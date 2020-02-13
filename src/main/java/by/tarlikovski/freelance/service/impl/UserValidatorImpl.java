package by.tarlikovski.freelance.service.impl;

import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;
import by.tarlikovski.freelance.service.UserValidator;

public class UserValidatorImpl extends ServiceImpl implements UserValidator {

    @Override
    public boolean validate(final User user) throws ServiceException {
        UserService service = (UserService) (new ServiceFactoryImpl()).getService(ServiceName.USER_SERVICE);
        if (service.findByLogin(user.getLogin()).isPresent()
                && !user.getLogin().equals("")) {
            throw new ServiceException("Login is not unique.");
        }
        if (service.findByEmail(user.getEmail()).isPresent()
                && !user.getEmail().equals("")) {
            throw new ServiceException("E-mail is not unique.");
        }
        return true;
    }
}
