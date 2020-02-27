package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.*;
import by.tarlikovski.freelance.service.impl.ServiceFactoryImpl;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WorkServiceTest {
    private int id;
    private ServiceFactoryImpl factory;
    private UserService userService;
    private OrderService orderService;
    private WorkService workService;

    @BeforeTest
    public void beforeTest() throws Exception {
        ServiceFactoryImpl.init();
        factory = new ServiceFactoryImpl();
        userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        workService = (WorkService) factory.getService(ServiceName.WORK_SERVICE);

    }

    @Test
    public void createTest_Equal() throws Exception {
        User user = userService.read(18).get();
        Order order = orderService.read(26).get();
        Work work = new Work();
        work.setUser(user);
        work.setOrder(order);
        work.setGrade((byte) 5);
        int v = workService.create(work);
        id = work.getId();
        Assert.assertEquals(v, 1);
    }

    @Test (dependsOnMethods = "createTest_Equal")
    public void findByUserTest_Equal() throws Exception {
        User user = userService.read(18).get();
        Order order = orderService.read(26).get();
        Work actual = workService.findByUser(user).get(0);
        Work expected = new Work();
        expected.setUser(user);
        expected.setOrder(order);
        expected.setGrade((byte) 5);
        expected.setStatus(Status.NOT_CONFIRMED);
        expected.setId(id);
        Assert.assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = "findByUserTest_Equal")
    public void readTest_True() throws Exception {
        User user = userService.read(18).get();
        Order order = orderService.read(26).get();
        Work actual = workService.read(id).get();
        Work expected = new Work();
        expected.setUser(user);
        expected.setOrder(order);
        expected.setGrade((byte) 5);
        expected.setStatus(Status.NOT_CONFIRMED);
        expected.setId(id);
        Assert.assertEquals(actual, expected);
    }

    @Test (dependsOnMethods = "readTest_True")
    public void findByOrder_Equal() throws Exception {
        User user = userService.read(18).get();
        Order order = orderService.read(26).get();
        Work actual = workService.findByOrder(order).get(0);
        Work expected = new Work();
        expected.setUser(user);
        expected.setOrder(order);
        expected.setGrade((byte) 5);
        expected.setStatus(Status.NOT_CONFIRMED);
        expected.setId(id);
        Assert.assertEquals(actual, expected);
    }

    @Test (dependsOnMethods = "findByOrder_Equal")
    public void updateTest_Equal() throws Exception {
        Work work = workService.read(id).get();
        work.setGrade((byte) 10);
        int i = workService.update(work);
        Assert.assertEquals(i, 1);
    }

    @Test (dependsOnMethods = "updateTest_Equal")
    public void deleteTest_Equal() throws Exception {
        int v = workService.delete(id);
        Assert.assertEquals(v, 1);
    }

    @AfterTest
    public void afterTest() throws Exception {
        workService.delete(id);
    }
}
