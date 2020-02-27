package by.tarlikovski.freelance.control.command.registered;

import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.Status;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.bean.Work;
import by.tarlikovski.freelance.control.command.Command;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.WorkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ConfirmWork extends Command {

    public ConfirmWork() {
        setAddress("/order");
    }
    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
            WorkService workService = (WorkService) factory.getService(ServiceName.WORK_SERVICE);
            int id = Integer.parseInt(request.getParameter("workid"));
            Optional<Work> work = workService.read(id);
            User curUser = (User) request.getSession().getAttribute("user");
            if (work.isPresent()
                    && work.get().getOrder().getClient().getId() == curUser.getId()) {
                work.get().setStatus(Status.CONFIRMED);
                workService.update(work.get());
                String path = request.getHeader("Referer");
                request.setAttribute("url", path);
                return "Redirect";
            } else {
                request.setAttribute("error", "noaccess");
                setAddress("/error");
                return "Error";
            }
    }
}
