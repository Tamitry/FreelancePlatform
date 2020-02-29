package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.Order;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.service.impl.ServiceFactoryImpl;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Timestamp;

public class OrderServiceTest {
    private int id;
    private ServiceFactory factory;
    private OrderService orderService;

    @BeforeTest(description = "Factory initiated.")
    public void beforeTest() throws Exception {
        ServiceFactoryImpl.init();
        factory = new ServiceFactoryImpl();
        orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
    }

    @Test(description = "Entity has been created.")
    public void createTest_Equal() throws Exception {
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        User user = userService.findByLogin("client").get();
        Order order = new Order();
        order.setOrderName("Test");
        order.setOrderDeadLine(Timestamp.valueOf("2029-12-12 00:00:00")); //2029-12-12
        order.setDescription("Test");
        order.setClient(user);
        int i = orderService.create(order);
        id = order.getId();
        Assert.assertEquals(i, 1);
    }

    @Test(dependsOnMethods = "createTest_Equal",
            description = "Find the order by name.")
    public void findByNameTest_True() throws Exception {
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        User user = userService.findByLogin("client").get();
        Order expected = new Order();
        expected.setOrderName("Test");
        expected.setOrderDeadLine(Timestamp.valueOf("2029-12-12 00:00:00")); //2029-12-12
        expected.setDescription("Test");
        expected.setId(id);
        expected.setClient(user);
        Order actual = orderService.findByName(expected.getOrderName()).get(0);
        Assert.assertTrue(expected.getClient().equals(actual.getClient()) &&
                expected.getOrderName().equals(actual.getOrderName()) &&
                expected.getDescription().equals(actual.getDescription()) &&
                //          order.getOrderDeadLine().equals(order.getOrderDeadLine()) &&
                expected.getClient().equals(actual.getClient()) &&
                expected.getId() == actual.getId());
    }

    @Test(dependsOnMethods = "findByNameTest_True",
            description = "Find the order by client.")
    public void findByUserTest_True() throws Exception {
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        User user = userService.findByLogin("client").get();
        Order order = new Order();
        order.setOrderName("Test");
        order.setOrderDeadLine(Timestamp.valueOf("2029-12-12 00:00:00")); //2029-12-12
        order.setDescription("Test");
        order.setId(id);
        order.setClient(user);
        Order actual = orderService.findByUser(user).get(1);
        Assert.assertTrue(order.getClient().equals(actual.getClient()) &&
                order.getOrderName().equals(actual.getOrderName()) &&
                //           order.getDescription().equals(actual.getDescription()) &&
                order.getOrderDeadLine().equals(order.getOrderDeadLine()) &&
                order.getId() == actual.getId());
    }

    @Test(dependsOnMethods = "findByUserTest_True",
            description = "Find all not outdated orders.")
    public void findAllTest_True() throws Exception {
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        User user = userService.findByLogin("client").get();
        Order order = new Order();
        order.setOrderName("Test");
        order.setOrderDeadLine(Timestamp.valueOf("2029-12-12 00:00:00")); //2029-12-12
        order.setDescription("Test");
        order.setId(id);
        order.setClient(user);
        Order actual = orderService.findAll().get(1);
        Assert.assertTrue(order.getClient().equals(actual.getClient()) &&
                order.getOrderName().equals(actual.getOrderName()) &&
                //           order.getDescription().equals(actual.getDescription()) &&
                order.getOrderDeadLine().equals(order.getOrderDeadLine()) &&
                order.getId() == actual.getId());
    }

    @Test(dependsOnMethods = "findAllTest_True")
    public void readTest_True() throws Exception {
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        User user = userService.findByLogin("client").get();
        Order order = new Order();
        order.setOrderName("Test");
        order.setOrderDeadLine(Timestamp.valueOf("2029-12-12 00:00:00")); //2029-12-12
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

    @Test(dependsOnMethods = "readTest_True",
            description = "Update orders.")
    public void updateTest_Equal() throws Exception {
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        User user = userService.findByLogin("client").get();
        Order order = new Order();
        order.setOrderName("TestUpdate");
        order.setOrderDeadLine(Timestamp.valueOf("2029-12-12 00:00:00")); //2029-12-12
        order.setDescription("TestUpdate");
        order.setId(id);
        order.setClient(user);
        int v = orderService.update(order);
        Assert.assertEquals(v, 1);
    }

    @Test(dependsOnMethods = "updateTest_Equal",
            description = "Delete order.")
    public void deleteTest_Equal() throws Exception {
        int i = orderService.delete(id);
        Assert.assertEquals(i, 1);
    }

    @AfterTest
    public void afterTest() throws Exception {
        orderService.delete(id);
    }
}
