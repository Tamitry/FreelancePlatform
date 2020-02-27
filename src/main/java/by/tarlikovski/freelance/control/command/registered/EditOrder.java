package by.tarlikovski.freelance.control.command.registered;

import by.tarlikovski.freelance.bean.*;
import by.tarlikovski.freelance.control.command.Command;
import by.tarlikovski.freelance.service.CategoryService;
import by.tarlikovski.freelance.service.OrderPropertyService;
import by.tarlikovski.freelance.service.OrderService;
import by.tarlikovski.freelance.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class EditOrder extends Command {

    public EditOrder() {
        setAddress("/order");
        Set<Role> roleSet = getRoles();
        roleSet.add(Role.CLIENT);
    }
    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        OrderService orderService = (OrderService) factory
                .getService(ServiceName.ORDER_SERVICE);
        OrderPropertyService propertyService = (OrderPropertyService) factory
                .getService(ServiceName.ORDER_PROPERTY_SERVICE);
        CategoryService categoryService = (CategoryService) factory
                .getService(ServiceName.CATEGORY_SERVICE);
        int id = Integer.parseInt(request.getParameter("orderid"));
        Optional<Order> order = orderService.read(id);
        User curUser = (User) request.getSession().getAttribute("user");
        if (order.isPresent() && order.get().getClient().getId() == curUser.getId()) {
            String date = request.getParameter("deadline");
            order.get().setOrderName(request.getParameter("ordername"));
            order.get().setOrderDeadLine(Timestamp.valueOf(date + " 00:00:00"));
            order.get().setDescription(request.getParameter("desc"));
            orderService.update(order.get());
            List<Category> categories = categoryService.findAll();
            for (Category category : categories) {
                String req = request.getParameter("" + category.getId());
                propertyService.delete(order.get(), category);
                if (req != null) {
                    propertyService.create(order.get(), category);
                }
            }
            request.setAttribute("url", request.getContextPath() + "/toorder.html?orderid=" + id);
            return "Redirect";
        } else {
            request.setAttribute("error", "noaccess");
            setAddress("/error");
            return "Error";
        }
    }
}
