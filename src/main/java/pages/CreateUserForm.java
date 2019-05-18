package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateUserForm {

    private final WebDriver driver;

    private final By fullName = By.id("id_l.U.cr.fullName");
    private final By email = By.id("id_l.U.cr.email");
    private final By login = By.id("id_l.U.cr.login");
    private final By password = By.id("id_l.U.cr.password");
    private final By confirmPassword = By.id("id_l.U.cr.confirmPassword");
    private final By forcePasswordChange = By.id("id_l.U.cr.forcePasswordChange");
    private final By okButton = By.id("id_l.U.cr.createUserOk");
    private final By cancelButton = By.id("id_l.U.cr.createUserCancel");
    private final By dialogMask = By.className("jt-dialog-mask");
    private static final String PAGE_URL = "http://localhost:8080/users";

    public CreateUserForm(WebDriver webDriver) {
        this.driver = webDriver;
        Utils.waitUntilLocator(new WebDriverWait(driver, 5), cancelButton);
    }

    public boolean registerUser(String fullName,
                                String email,
                                String login,
                                String password,
                                String passwordConfirmation,
                                boolean forcePasswordChange) {
        setFullName(fullName);
        setEmail(email);
        setLogin(login);
        setPassword(password);
        setConfirmPassword(passwordConfirmation);
        setForcePasswordChange(forcePasswordChange);
        clickOkButton();

        Utils.waitForMilliseconds(driver, 500);

        boolean success = !isOpen();
        if (success) {
            driver.navigate().back();
        }
        return success;
    }

    public boolean isOpen() {
        if (!driver.getCurrentUrl().equals(PAGE_URL)) {
            return false;
        }
        return Utils.isPresent(driver, dialogMask);
    }


    public void clickOkButton() {
        driver.findElement(okButton).click();
    }

    public void clickCancelButton() {
        driver.findElement(cancelButton).click();
    }

    public void setFullName(String text) {
        driver.findElement(fullName).sendKeys(text);
    }

    public void setEmail(String text) {
        driver.findElement(email).sendKeys(text);
    }

    public void setLogin(String text) {
        driver.findElement(login).sendKeys(text);
    }

    public void setPassword(String text) {
        driver.findElement(password).sendKeys(text);
    }

    public void setConfirmPassword(String text) {
        driver.findElement(confirmPassword).sendKeys(text);
    }

    public void setForcePasswordChange(boolean isSet) {
        WebElement checkbox = driver.findElement(forcePasswordChange);

        if (checkbox.isSelected() != isSet) {
            checkbox.click();
        }
    }
}
