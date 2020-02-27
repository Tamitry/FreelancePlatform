package by.tarlikovski.freelance.service.impl;

import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;
import by.tarlikovski.freelance.service.Validator;

public class UserLogValidatorImpl extends ServiceImpl implements Validator<User> {

    private static final String LOGIN_PATTERN = "\\w{5,16}";

    @Override
    public boolean validate(final User user)
            throws ServiceException {
        UserService service = (UserService) (new ServiceFactoryImpl()).getService(ServiceName.USER_SERVICE);
        if (!user.getLogin().matches(LOGIN_PATTERN)) {
            throw new ServiceException("loginpattern");
        }
        if (!service.findByLogin(user.getLogin()).isPresent()) {
            throw new ServiceException("usernotexist");
        }
        return true;
    }
}
