package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.service.impl.ServiceFactoryImpl;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class SkillServiceTest {

    private ServiceFactory factory;
    private UserService userService;
    private SkillService skillService;
    private CategoryService categoryService;

    @BeforeTest
    public void beforeTest() throws Exception {
        ServiceFactoryImpl.init();
        factory = new ServiceFactoryImpl();
        skillService = (SkillService) factory.getService(ServiceName.SKILL_SERVICE);
        userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        categoryService = (CategoryService) factory.getService(ServiceName.CATEGORY_SERVICE);
    }

    @Test
    public void createSkillTest_Equal() throws Exception {
        User user = userService.read(20).get();
        Category category = categoryService.findAll().get(0);
        int v = skillService.createSkill(user, category);
        Assert.assertEquals(v, 1);
    }

    @Test (dependsOnMethods = "createSkillTest_Equal")
    public void findUserSkillTest_Equal() throws Exception {
        Category category = categoryService.findAll().get(0);
        User user = userService.read(20).get();
        List<Category> categories = skillService.findUserSkills(user);
        Assert.assertEquals(categories.get(0), category);
    }

    @Test (dependsOnMethods = "findUserSkillTest_Equal")
    public void findUsersBySkillTest_True() throws Exception {
        Category category = categoryService.findAll().get(0);
        User expected = userService.read(20).get();
        User actual = skillService.findUsersBySkill(category).get(0);
        Assert.assertTrue(expected.getEmail().equals(actual.getEmail()) &&
                expected.getLastName().equals(actual.getLastName()) &&
                expected.getFirstName().equals(actual.getFirstName()) &&
                expected.getPassword().equals(actual.getPassword()) &&
                expected.getLogin().equals(actual.getLogin()) &&
                expected.getRole().equals(actual.getRole()) &&
                expected.getId() == actual.getId());
    }

    @Test (dependsOnMethods = "findUsersBySkillTest_True")
    public void deleteSkillTest_Equal() throws Exception {
        User user = userService.read(20).get();
        Category category = categoryService.findAll().get(0);
        int v = skillService.deleteSkill(user, category);
        Assert.assertEquals(v, 1);
    }

    @AfterTest
    public void afterTest() throws Exception {
        User user = userService.read(20).get();
        Category category = categoryService.findAll().get(0);
        skillService.deleteSkill(user, category);
    }
}
