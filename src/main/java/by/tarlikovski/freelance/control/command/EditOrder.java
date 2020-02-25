package by.tarlikovski.freelance.control.command;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.service.CategoryService;
import by.tarlikovski.freelance.service.OrderPropertyService;
import by.tarlikovski.freelance.service.OrderService;
import by.tarlikovski.freelance.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;

public class EditOrder extends Command {

    public EditOrder() {
        setAddress("/order");
    }
    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        int id = Integer.parseInt(request.getParameter("orderid"));
        OrderService orderService = (OrderService) factory
                .getService(ServiceName.ORDER_SERVICE);
        OrderPropertyService propertyService = (OrderPropertyService) factory
                .getService(ServiceName.ORDER_PROPERTY_SERVICE);
        CategoryService categoryService = (CategoryService) factory
                .getService(ServiceName.CATEGORY_SERVICE);
        Order order = orderService.read(id).get();
        String date = request.getParameter("deadline");
        order.setOrderName(request.getParameter("ordername"));
        order.setOrderDeadLine(Timestamp.valueOf(date + " 00:00:00"));
        order.setDescription(request.getParameter("desc"));
        orderService.update(order);
        List<Category> categories = categoryService.findAll();
        for (Category category : categories) {
             String req = request.getParameter("" + category.getId());
            propertyService.delete(order, category);
            if (req != null) {
                propertyService.create(order, category);
            }
        }
        request.setAttribute("url", request.getContextPath() + "/toorder.html?orderid=" + id);
        return "Redirect";
    }
}
