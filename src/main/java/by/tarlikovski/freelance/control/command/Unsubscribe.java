package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.bean.Work;
import by.tarlikovski.freelance.service.OrderService;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;
import by.tarlikovski.freelance.service.WorkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Unsubscribe extends Command {

    public Unsubscribe() {
        setAddress("/order");
    }
    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        WorkService workService = (WorkService) factory.getService(ServiceName.WORK_SERVICE);
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        int orderId = Integer.parseInt(request.getParameter("orderid"));
        int userId = Integer.parseInt(request.getParameter("userid"));
        User user = userService.read(userId).get();
        List<Work> works = workService.findByUser(user);
        for (Work work : works) {
            if (work.getOrder().getId() == orderId) {
                workService.delete(work.getId());
            }
        }
        String path = request.getHeader("Referer");
        request.setAttribute("url", path);
        return "Redirect";
    }
}
