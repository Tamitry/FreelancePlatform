package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.*;
import by.tarlikovski.freelance.service.impl.ServiceFactoryImpl;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class OrderPropertyServiceTest {

    private ServiceFactory serviceFactory;
    private OrderPropertyService propertyService;
    private OrderService orderService;
    private CategoryService categoryService;

    @BeforeTest
    public void beforeTest() throws Exception {
        ServiceFactoryImpl.init();
        serviceFactory = new ServiceFactoryImpl();
        propertyService =
                (OrderPropertyService) serviceFactory.getService(ServiceName.ORDER_PROPERTY_SERVICE);
        orderService = (OrderService) serviceFactory.getService(ServiceName.ORDER_SERVICE);
        categoryService = (CategoryService) serviceFactory.getService(ServiceName.CATEGORY_SERVICE);
    }

    @Test (description = "Object creation.")
    public void createTest_Equal() throws Exception {
        Order order = orderService.read(26).get();
        Category category = categoryService.findAll().get(0);
        int v = propertyService.create(order, category);
        Assert.assertEquals(v, 1);
    }
    @Test (dependsOnMethods = "createTest_Equal", description = "Find prepared in advance order's categories.")
    public void findByOrderTest_Equal() throws Exception {
        Order order = orderService.read(26).get();
        Category actual = propertyService.findByOrder(order).get(0);
        Category expected = categoryService.findAll().get(0);
        Assert.assertEquals(actual, expected);
    }

    @Test (dependsOnMethods = "findByOrderTest_Equal", description = "Find list of orders by category.")
    public void findByCategoryTest_Equal() throws Exception {
        Category category = categoryService.findAll().get(0);
        Order actual = propertyService.findByCategory(category).get(0);
        Order expected = orderService.read(26).get();
        Assert.assertEquals(actual, expected);
    }

    @Test (dependsOnMethods = "findByCategoryTest_Equal", description = "Delete created object.")
    public void deleteTest_Equal() throws Exception {
        Order order = orderService.read(26).get();
        Category category = categoryService.findAll().get(0);
        int v = propertyService.delete(order, category);
        Assert.assertEquals(v, 1);
    }

    @AfterTest
    public void afterTest() throws Exception {
        Order order = orderService.read(26).get();
        Category category = categoryService.findAll().get(0);
        propertyService.delete(order, category);
    }
}