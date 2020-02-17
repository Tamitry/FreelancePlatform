package by.tarlikovski.freelance.control.filters;

import by.tarlikovski.freelance.control.command.*;
import by.tarlikovski.freelance.control.command.Registration;
import by.tarlikovski.freelance.control.ControlException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    private Command findCommand(final String name) throws ControlException {
        switch (name) {
            case "/userlist":
                return new UserList();
            case "/toregistration":
                return new ToRegistration();
            case "/registration":
                return new Registration();
            case "/home":
                return new Home();
            case "/error":
                return new ErrorPage();
            case "/login":
                return new Login();
            case "/tologin":
                return new ToLogin();
            case "/toprofile":
                return new ToProfile();
            case "/exit":
                return new Exit();
            case "/toeditprofile":
                return new ToEditProfile();
            case "/orderlist":
                return new OrderList();
            case "/saveprofile":
                return new EditProfile();
            case "/toaddprofile":
                return new ToAddOrder();
            case "/deleteuser":
                return new DeleteUser();
            case "/confirmwork":
                return new ConfirmWork();
            case "/cancelwork":
                return new CancelWork();
            case "/unsubscribe":
                return new Unsubscribe();
            case "/subscribe":
                return new Subscribe();
            case "/toorder":
                return new ToOrder();
            default:
                throw new ControlException("Command not found!");
        }
    }

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String contextPath = req.getContextPath();
        String uri = req.getRequestURI();
        int beginAction = contextPath.length();
        int endAction = uri.lastIndexOf('.');
        String actionName;
        if (endAction >= 0) {
            actionName = uri.substring(beginAction, endAction);

        } else {
            actionName = uri.substring(beginAction);
        }
        try {
            req.setAttribute("command", findCommand(actionName));
            filterChain.doFilter(req, resp);
        } catch (ControlException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    public void destroy() {

    }
}
