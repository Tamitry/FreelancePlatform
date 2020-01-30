package by.tarlikovski.freelance.control;

import by.tarlikovski.freelance.control.command.Command;
import by.tarlikovski.freelance.exception.PersistentException;
import by.tarlikovski.freelance.service.ServiceFactoryImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ServiceFactoryImpl.init();
        } catch (PersistentException e) {
            throw new ServletException(e);
        }
    }

    private void process(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws IOException, ServletException {
        Command command = (Command) req.getAttribute("command");
        try {
            command.setFactory(new ServiceFactoryImpl());
            command.exec(req, resp);
        } catch (PersistentException e) {
            throw new ServletException(e);
        }
        String jspPage = "/WEB-INF/views" + command.getName() + ".jsp";
        getServletContext().getRequestDispatcher(jspPage).forward(req, resp);
    }
}
