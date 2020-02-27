package by.tarlikovski.freelance.service.impl;

import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;
import by.tarlikovski.freelance.service.Validator;

public class UserRegValidatorImpl extends ServiceImpl implements Validator<User> {

    private static final String EMAIL_PATTERN = "\\S*@\\S*\\.\\S*";
    private static final String LOGIN_PATTERN = "\\w{5,16}";
    private static final String PASS_PATTERN = "[A-Za-z0-9]{6,16}";

    @Override
    public boolean validate(final User user) throws ServiceException {
        UserService service = (UserService) (new ServiceFactoryImpl()).getService(ServiceName.USER_SERVICE);
        if (service.findByLogin(user.getLogin()).isPresent()
                && !user.getLogin().equals("")) {
            throw new ServiceException("loginunique");
        }
        if (service.findByEmail(user.getEmail()).isPresent()
                && !user.getEmail().equals("")) {
            throw new ServiceException("emailunique");
        }
        if (!user.getEmail().matches(EMAIL_PATTERN)) {
            throw new ServiceException("emailpattern");
        }
        if (!user.getLogin().matches(LOGIN_PATTERN)) {
            throw new ServiceException("loginpattern");
        }
        if (!user.getPassword().matches(PASS_PATTERN)
                && !user.getPassword().matches("[A-Z]")
                && !user.getPassword().matches("[a-z]")
                && !user.getPassword().matches("[0-9]")) {
            throw new ServiceException("passpattern");
        }
        return true;
    }
}
