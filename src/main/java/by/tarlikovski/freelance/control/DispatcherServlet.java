package by.tarlikovski.freelance.control;

import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.control.command.Command;
import by.tarlikovski.freelance.control.command.CommandManager;
import by.tarlikovski.freelance.control.command.CommandManagerFactory;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.impl.ServiceFactoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(DispatcherServlet.class);

    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ServiceFactoryImpl.init();
        } catch (ServiceException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
    }

    private void process(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws IOException, ServletException {
        Command command = (Command) req.getAttribute("command");
        String res;
        try {
            CommandManager cm = CommandManagerFactory.getManager();
            res = cm.execute(command, req, resp);
        } catch (ControlException e) {
            LOGGER.error(e);
            res = "error";
            command.setAddress("/error");
        }
        if (req.getSession().getAttribute("user") != null) {
            User user = (User) req.getSession().getAttribute("user");
            user.setPassword(null);
            req.setAttribute("curuser", user);
        }
        String jspPage;
        if (!res.equals("Redirect")) {
            jspPage = "/WEB-INF/views" + command.getAddress() + ".jsp";
            getServletContext().getRequestDispatcher(jspPage).forward(req, resp);
            } else {
                jspPage = (String) req.getAttribute("url");
                resp.sendRedirect(jspPage);
            }
    }
}
