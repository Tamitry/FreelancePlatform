package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.Role;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.exception.PersistentException;
import by.tarlikovski.freelance.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

abstract public class Command {
    private Set<Role> roles = new HashSet<>();
    private User user;
    private String name;
    protected ServiceFactory factory;

    public Set<Role> getRoles() {
        return roles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User authorizedUser) {
        this.user = authorizedUser;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setFactory(final ServiceFactory factory) {
        this.factory = factory;
    }

    abstract public String exec(final HttpServletRequest request,
                                final HttpServletResponse response)
            throws PersistentException;
}