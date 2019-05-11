package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateUserForm {

    private final WebDriver driver;

    private final By fullName = By.id("id_l.U.cr.fullName");
    private final By email = By.id("id_l.R.user_email");
    private final By login = By.id("id_l.U.cr.login");
    private final By password = By.id("id_l.U.cr.password");
    private final By confirmPassword = By.id("id_l.U.cr.password");
    private final By forcePasswordChange = By.id("id_l.U.cr.forcePasswordChange");
    private final By okButton = By.id("id_l.U.cr.createUserOk");
    private final By cancelButton = By.id("id_l.U.cr.createUserCancel");

    public CreateUserForm(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void registerUser(String fullName,
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
