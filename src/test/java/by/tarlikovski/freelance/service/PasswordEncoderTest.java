package by.tarlikovski.freelance.service;

import by.tarlikovski.freelance.service.impl.PasswordEncoderImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PasswordEncoderTest {
    @Test(description = "It encode password and then checked equality of encoded and initial password.")
    public void checkTest_True() throws ServiceException {
        String pass = "1234";
        PasswordEncoder passwordEncoder = new PasswordEncoderImpl();
        String encryptedPass = passwordEncoder.getSaltedHash(pass);
        Assert.assertTrue(passwordEncoder.check(pass, encryptedPass));
    }
}
