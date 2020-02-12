package by.tarlikovski.freelance.control.filters;

import by.tarlikovski.freelance.bean.Role;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.control.command.Command;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class SecurityFilter implements Filter {
    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Command command = (Command) request.getAttribute("command");
        HttpSession session = request.getSession();
        Set<Role> allowedRoles = command.getRoles();
        User user = null;
        user = (User) request.getAttribute("curuser");
        boolean canExecute = allowedRoles.isEmpty();
        if (user != null) {
            canExecute = canExecute || allowedRoles.contains(user.getRole());
        }
        if (canExecute) {
            filterChain.doFilter(request, response);
        } else {
            request.setAttribute("error", "Some error"); //TODO
            request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/error.jsp")
                    .forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
