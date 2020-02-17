package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.service.impl.ServiceFactoryImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class OrderPropertyServiceTest {

    @Test (description = "Cre")
    public void createTest_Equal() throws Exception {
        ServiceFactoryImpl.init();
        ServiceFactoryImpl factory = new ServiceFactoryImpl();
        OrderPropertyService propertyService =
                (OrderPropertyService) factory.getService(ServiceName.ORDER_PROPERTY_SERVICE);
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        CategoryService categoryService = (CategoryService) factory.getService(ServiceName.CATEGORY_SERVICE);
        Order order = orderService.read(26).get();
        Category category = categoryService.findAll().get(0);
        int v = propertyService.create(order, category);
        Assert.assertEquals(v, 1);
    }
    @Test (dependsOnMethods = "createTest_Equal")
    public void findByOrderTest_Equal() throws Exception {
        ServiceFactoryImpl.init();
        ServiceFactoryImpl factory = new ServiceFactoryImpl();
        OrderPropertyService propertyService =
                (OrderPropertyService) factory.getService(ServiceName.ORDER_PROPERTY_SERVICE);
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        CategoryService categoryService = (CategoryService) factory.getService(ServiceName.CATEGORY_SERVICE);
        Order order = orderService.read(26).get();
        Category actual = propertyService.findByOrder(order).get(0);
        Category expected = categoryService.findAll().get(0);
        Assert.assertEquals(actual, expected);
    }

    @Test (dependsOnMethods = "findByOrderTest_Equal")
    public void findByCategoryTest_Equal() throws Exception {
        ServiceFactoryImpl.init();
        ServiceFactoryImpl factory = new ServiceFactoryImpl();
        OrderPropertyService propertyService =
                (OrderPropertyService) factory.getService(ServiceName.ORDER_PROPERTY_SERVICE);
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        CategoryService categoryService = (CategoryService) factory.getService(ServiceName.CATEGORY_SERVICE);
        Category category = categoryService.findAll().get(0);
        Order actual = propertyService.findByCategory(category).get(0);
        Order expected = orderService.read(26).get();
        Assert.assertEquals(actual, expected);
    }

    @Test (dependsOnMethods = "findByCategoryTest_Equal")
    public void deleteTest_Equal() throws Exception {
        ServiceFactoryImpl.init();
        ServiceFactoryImpl factory = new ServiceFactoryImpl();
        OrderPropertyService propertyService =
                (OrderPropertyService) factory.getService(ServiceName.ORDER_PROPERTY_SERVICE);
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        CategoryService categoryService = (CategoryService) factory.getService(ServiceName.CATEGORY_SERVICE);
        Order order = orderService.read(26).get();
        Category category = categoryService.findAll().get(0);
        int v = propertyService.delete(order, category);
        Assert.assertEquals(v, 1);
    }
}