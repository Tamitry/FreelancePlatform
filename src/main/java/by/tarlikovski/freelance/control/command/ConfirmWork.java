package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.Status;
import by.tarlikovski.freelance.bean.Work;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.WorkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            Work work = workService.read(id).get();
            work.setStatus(Status.CONFIRMED);
            workService.update(work);
            String path = request.getHeader("Referer");
            request.setAttribute("url", path);
            return "Redirect";
    }
}
