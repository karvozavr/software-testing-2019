import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CreateUserForm;
import pages.UsersPage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CreateUserLoginTest {

    private WebDriver driver;
    private static final String baseUrl = "localhost:8080/users";
    private static final String PASSWORD = "password";
    private static final String EMAIL_SUFFIX = "@domain.org";
    private int userId = 0;
    private UsersPage usersPage;
    private List<String> usernames = new ArrayList<>();

    private boolean registerUser(String username) {
        CreateUserForm form = usersPage.openCreateUserForm();
        ++userId;
        boolean success = form.registerUser(username, "e" + userId + EMAIL_SUFFIX, username, PASSWORD, PASSWORD, false);
        if (success) {
            usernames.add(username);
        }
        return success;
    }

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "/home/karvozavr/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.get(baseUrl);
        usersPage = new UsersPage(driver);
    }

    @Test
    public void testSmoke() {
        String expectedTitle = "Users | YouTrack";
        assertEquals(expectedTitle, driver.getTitle());
    }

    @Test
    public void testLogin1() {
        assertTrue(registerUser("username"));
    }

    @Test
    public void testLogin2() {
        assertTrue(registerUser("username1"));
    }

    @Test
    public void testLogin3() {
        assertTrue(registerUser("UserName"));
    }

    @Test
    public void testLogin4() {
        assertTrue(registerUser("Константин"));
    }

    @Test
    public void testLogin5() {
        assertTrue(registerUser("123"));
    }

    @Test
    public void testLogin6() {
        assertTrue(registerUser(":)"));
    }

    @Test
    public void testLoginNeg1() {
        assertFalse(registerUser(""));
    }

    @Test
    public void testLoginNeg2() {
        assertFalse(registerUser("; DROP TABLE Users;"));
    }

    @Test
    public void testLoginNeg3() {
        assertFalse(registerUser("user name"));
    }

    @Test
    public void testLoginNeg4() {
        assertFalse(registerUser("</img>"));
    }

    @After
    public void after() {
        driver.get(baseUrl);
        usernames.forEach(usersPage::deleteUser);
        driver.close();
    }
}
