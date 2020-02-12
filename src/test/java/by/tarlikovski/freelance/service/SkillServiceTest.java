package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.bean.Category;
import by.tarlikovski.freelance.bean.ServiceName;
import by.tarlikovski.freelance.bean.Skill;
import by.tarlikovski.freelance.bean.User;
import by.tarlikovski.freelance.service.impl.ServiceFactoryImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SkillServiceTest {
    @Test
    public void createSkillTest_Equal() throws Exception {
        ServiceFactoryImpl.init();
        ServiceFactoryImpl factory = new ServiceFactoryImpl();
        SkillService skillService = (SkillService) factory.getService(ServiceName.SKILL_SERVICE);
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        CategoryService categoryService = (CategoryService) factory.getService(ServiceName.CATEGORY_SERVICE);
        User user = userService.findById(20).get();
        Category category = categoryService.findAll().get(0);
        int v = skillService.createSkill(user, category);
        Assert.assertEquals(v, 1);
    }

    @Test (dependsOnMethods = "createSkillTest_Equal")
    public void findUserSkillTest_Equal() throws Exception {
        ServiceFactoryImpl.init();
        ServiceFactoryImpl factory = new ServiceFactoryImpl();
        SkillService skillService = (SkillService) factory.getService(ServiceName.SKILL_SERVICE);
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        CategoryService categoryService = (CategoryService) factory.getService(ServiceName.CATEGORY_SERVICE);
        Category category = categoryService.findAll().get(0);
        User user = userService.findById(20).get();
        List<Category> categories = skillService.findUserSkills(user);
        Assert.assertEquals(categories.get(0), category);
    }

    @Test (dependsOnMethods = "findUserSkillTest_Equal")
    public void deleteSkillTest_Equal() throws Exception {
        ServiceFactoryImpl.init();
        ServiceFactoryImpl factory = new ServiceFactoryImpl();
        SkillService skillService = (SkillService) factory.getService(ServiceName.SKILL_SERVICE);
        UserService userService = (UserService) factory.getService(ServiceName.USER_SERVICE);
        CategoryService categoryService = (CategoryService) factory.getService(ServiceName.CATEGORY_SERVICE);
        User user = userService.findById(20).get();
        Category category = categoryService.findAll().get(0);
        int v = skillService.deleteSkill(user, category);
        Assert.assertEquals(v, 1);
    }
}
