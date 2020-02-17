package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.Role;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.control.ControlException;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

abstract public class Command {
    private Set<Role> roles = new HashSet<>();
    private User user;
    private String address;
    protected ServiceFactory factory;

    public void setRoles(final Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User authorizedUser) {
        this.user = authorizedUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setFactory(final ServiceFactory factory) {
        this.factory = factory;
    }

    abstract public String exec(final HttpServletRequest request,
                                final HttpServletResponse response)
            throws ServiceException;
}
