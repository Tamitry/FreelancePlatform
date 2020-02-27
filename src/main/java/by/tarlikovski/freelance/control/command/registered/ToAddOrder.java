package by.tarlikovski.freelance.control.command.registered;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.Role;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.control.command.Command;
import by.tarlikovski.freelance.service.CategoryService;
import by.tarlikovski.freelance.service.OrderService;
import by.tarlikovski.freelance.service.ServiceException;
import by.tarlikovski.freelance.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

public class ToAddOrder extends Command {

    public ToAddOrder() {
        setAddress("/addorder");
        Set<Role> roleSet = getRoles();
        roleSet.add(Role.CLIENT);
    }

    @Override
    public String exec(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServiceException {
        CategoryService categoryService = (CategoryService) factory.getService(ServiceName.CATEGORY_SERVICE);
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories", categories);
        return "To add order";
    }
}
