package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.service.impl.ServiceFactoryImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Timestamp;

public class OrderServiceTest {
    private int id;

    @Test
    public void createTest_Equal() throws Exception {
        ServiceFactoryImpl.init();
        ServiceFactoryImpl factory = new ServiceFactoryImpl();
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        User user = userService.findByLogin("client").get();
        Order order = new Order();
        order.setOrderName("Test");
        order.setOrderDeadLine(new Timestamp(1891717200)); //2029-12-12
        order.setDescription("Test");
        order.setClient(user);
        int i = orderService.create(order);
        id = order.getId();
        Assert.assertEquals(i, 1);
    }

    @Test(dependsOnMethods = "createTest_Equal")
    public void findByNameTest_True() throws Exception {
        ServiceFactoryImpl.init();
        ServiceFactoryImpl factory = new ServiceFactoryImpl();
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        User user = userService.findByLogin("client").get();
        Order order = new Order();
        order.setOrderName("Test");
        order.setOrderDeadLine(new Timestamp(1891717200)); //2029-12-12
        order.setDescription("Test");
        order.setId(id);
        order.setClient(user);
        Order actual = orderService.findByName(order.getOrderName()).get(0);
        Assert.assertTrue(order.getClient().equals(actual.getClient()) &&
                order.getOrderName().equals(actual.getOrderName()) &&
                order.getDescription().equals(actual.getDescription()) &&
                //          order.getOrderDeadLine().equals(order.getOrderDeadLine()) &&
                order.getClient().equals(actual.getClient()) &&
                order.getId() == actual.getId());
    }

    @Test(dependsOnMethods = "findByNameTest_True")
    public void findByUserTest_True() throws Exception {
        ServiceFactoryImpl.init();
        ServiceFactoryImpl factory = new ServiceFactoryImpl();
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        User user = userService.findByLogin("client").get();
        Order order = new Order();
        order.setOrderName("Test");
        order.setOrderDeadLine(new Timestamp(1891717200)); //2029-12-12
        order.setDescription("Test");
        order.setId(id);
        order.setClient(user);
        Order actual = orderService.findByUser(user).get(0);
        Assert.assertTrue(order.getClient().equals(actual.getClient()) &&
                order.getOrderName().equals(actual.getOrderName()) &&
                //           order.getDescription().equals(actual.getDescription()) &&
                order.getOrderDeadLine().equals(order.getOrderDeadLine()) &&
                order.getId() == actual.getId());
    }

    @Test(dependsOnMethods = "findByUserTest_True")
    public void findAllTest_True() throws Exception {
        ServiceFactoryImpl.init();
        ServiceFactoryImpl factory = new ServiceFactoryImpl();
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        User user = userService.findByLogin("client").get();
        Order order = new Order();
        order.setOrderName("Test");
        order.setOrderDeadLine(new Timestamp(1891717200)); //2029-12-12
        order.setDescription("Test");
        order.setId(id);
        order.setClient(user);
        Order actual = orderService.findAll().get(0);
        Assert.assertTrue(order.getClient().equals(actual.getClient()) &&
                order.getOrderName().equals(actual.getOrderName()) &&
                //           order.getDescription().equals(actual.getDescription()) &&
                order.getOrderDeadLine().equals(order.getOrderDeadLine()) &&
                order.getId() == actual.getId());
    }

    @Test(dependsOnMethods = "findAllTest_True")
    public void readTest_True() throws Exception {
        ServiceFactoryImpl.init();
        ServiceFactoryImpl factory = new ServiceFactoryImpl();
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        User user = userService.findByLogin("client").get();
        Order order = new Order();
        order.setOrderName("Test");
        order.setOrderDeadLine(new Timestamp(1891717200)); //2029-12-12
        order.setDescription("Test");
        order.setId(id);
        order.setClient(user);
        Order actual = orderService.read(id).get();
        Assert.assertTrue(order.getClient().equals(actual.getClient()) &&
                order.getOrderName().equals(actual.getOrderName()) &&
                //           order.getDescription().equals(actual.getDescription()) &&
                order.getOrderDeadLine().equals(order.getOrderDeadLine()) &&
                order.getId() == actual.getId());
    }

    @Test(dependsOnMethods = "readTest_True")
    public void updateTest_Equal() throws Exception {
        ServiceFactoryImpl.init();
        ServiceFactoryImpl factory = new ServiceFactoryImpl();
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        User user = userService.findByLogin("client").get();
        Order order = new Order();
        order.setOrderName("TestUpdate");
        order.setOrderDeadLine(new Timestamp(1891717200)); //2029-12-12
        order.setDescription("TestUpdate");
        order.setId(id);
        order.setClient(user);
        int v = orderService.update(order);
        Assert.assertEquals(v, 1);
    }

    @Test(dependsOnMethods = "updateTest_Equal")
    public void deleteTest_Equal() throws Exception {
        ServiceFactoryImpl.init();
        ServiceFactoryImpl factory = new ServiceFactoryImpl();
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        int i = orderService.delete(id);
        Assert.assertEquals(i, 1);
    }
}
