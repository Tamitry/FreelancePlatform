package by.tarlikovski.freelance.control.filters;

import by.tarlikovski.freelance.bean.Role;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.control.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class SecurityFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(SecurityFilter.class);

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Command command = (Command) request.getAttribute("command");
        Set<Role> allowedRoles = command.getRoles();
        User user = (User) request.getSession().getAttribute("user");
        boolean canExecute = allowedRoles.isEmpty();
        if (user != null) {
            canExecute = canExecute || allowedRoles.contains(user.getRole());
        }
        if (canExecute) {
            filterChain.doFilter(request, response);
        } else {
            LOGGER.info("User has no access to this command:" + (user != null ? "User login = " + user.getLogin() + ", " : "") + "Command = " + command);
            request.setAttribute("error", "noaccess");
            request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/error.jsp")
                    .forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
