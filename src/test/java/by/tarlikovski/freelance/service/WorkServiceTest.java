package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.*;
import by.tarlikovski.freelance.service.impl.ServiceFactoryImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkServiceTest {
    private static int id;
    @Test
    public void createTest_Equal() throws Exception {
        ServiceFactoryImpl.init();
        ServiceFactoryImpl factory = new ServiceFactoryImpl();
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        WorkService workService = (WorkService) factory.getService(ServiceName.WORK_SERVICE);
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
        ServiceFactoryImpl.init();
        ServiceFactoryImpl factory = new ServiceFactoryImpl();
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        WorkService workService = (WorkService) factory.getService(ServiceName.WORK_SERVICE);
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

    @Test (dependsOnMethods = "findByUserTest_Equal")
    public void findByOrder_Equal() throws Exception {
        ServiceFactoryImpl.init();
        ServiceFactoryImpl factory = new ServiceFactoryImpl();
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        OrderService orderService = (OrderService) factory.getService(ServiceName.ORDER_SERVICE);
        WorkService workService = (WorkService) factory.getService(ServiceName.WORK_SERVICE);
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
    public void deleteTest_Equal() throws Exception {
        ServiceFactoryImpl.init();
        ServiceFactoryImpl factory = new ServiceFactoryImpl();
        WorkService workService = (WorkService) factory.getService(ServiceName.WORK_SERVICE);
        int v = workService.delete(id);
        Assert.assertEquals(v, 1);
    }
}
