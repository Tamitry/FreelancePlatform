package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.Status;
import by.tarlikovski.freelance.bean.Work;
import by.tarlikovski.freelance.service.OrderService;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;
import by.tarlikovski.freelance.service.WorkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Subscribe extends Command {

    public Subscribe() {
        setAddress("/order");
    }
    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        WorkService workService = (WorkService) factory.getService(ServiceName.WORK_SERVICE);
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        int orderId = Integer.parseInt(request.getParameter("orderid"));
        int userId = Integer.parseInt(request.getParameter("userid"));
        Work work = new Work();
        work.setOrder(orderService.read(orderId).get());
        work.setUser(userService.read(userId).get());
        work.setStatus(Status.NOT_CONFIRMED);
        work.setGrade((byte) 5);
        workService.create(work);
        String path = request.getHeader("Referer");
        request.setAttribute("url", path);
        return "Redirect";
    }
}
