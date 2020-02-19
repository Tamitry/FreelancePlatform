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
import java.util.ArrayList;
import java.util.List;

public class ToEditOrder extends Command {

    public ToEditOrder() {
        setAddress("/toeditorder");
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        int id = Integer.parseInt(request.getParameter("orderid"));
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        CategoryService catService = (CategoryService) factory.getService(ServiceName.CATEGORY_SERVICE);
        OrderPropertyService propertyService = (OrderPropertyService) factory.getService(ServiceName.ORDER_PROPERTY_SERVICE);
        Order order = orderService.read(id).get();
        List<Category> orderCategories = propertyService.findByOrder(order);
        List<Category> allCategories = catService.findAll();
        List<Category> lastCategories = new ArrayList<>();
        for (Category category : allCategories) {
            boolean is = false;
            for (Category cat : orderCategories) {
                if (cat.getId() == category.getId()) {
                    is = true;
                }
            }
            if (!is) {
                lastCategories.add(category);
            }
        }
        request.setAttribute("properties", orderCategories);
        request.setAttribute("lastcategories", lastCategories);
        request.setAttribute("order", order);
        return "To edit profile.";
    }
}
