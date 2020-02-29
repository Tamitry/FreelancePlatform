package by.tarlikovski.freelance.service.impl;

import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.service.*;

public class UserLogValidatorImpl extends ServiceImpl implements Validator<User> {

    private static final String LOGIN_PATTERN = "\\w{2,16}";

    @Override
    public boolean validate(final User user)
            throws ServiceException {
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        UserService service = (UserService) serviceFactory.getService(ServiceName.USER_SERVICE);
        PasswordEncoder encoder = (PasswordEncoder) serviceFactory.getService(ServiceName.ENCODER);
        User expected = new User();
        if (!user.getLogin().matches(LOGIN_PATTERN)) {
            throw new ServiceException("loginpattern");
        }
        if (!service.findByLogin(user.getLogin()).isPresent()) {
            throw new ServiceException("usernotexist");
        }
        expected = service.findByLogin(user.getLogin()).get();
        if (!encoder.check(user.getPassword(), expected.getPassword())) {
            throw new ServiceException("wrongpassword");
        }
        return true;
    }
}
