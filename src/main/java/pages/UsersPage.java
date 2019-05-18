package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UsersPage {

    private final WebDriver driver;
    private WebDriverWait wait;

    private By searchField = By.id("id_l.U.queryText");
    private By searchButton = By.id("id_l.U.searchButton");
    private By resetButton = By.id("id_l.U.resetButton");
    private By deleteButtonSelector = By.cssSelector("a[cn=\"l.U.usersList.deleteUser\"]");
    private By createUserButton = By.id("id_l.U.createNewUser");

    public UsersPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
        Utils.waitUntilLocator(wait, createUserButton);
    }

    public CreateUserForm openCreateUserForm() {
        clickCreateUserButton();
        return new CreateUserForm(driver);
    }

    public boolean checkUserExists(String userName) {
        performSearh(userName);
        By userNameSelector = By.cssSelector("div[id=\"id_l.U.usersList.usersList\"] table");
        boolean exists = Utils.isPresent(driver, userNameSelector);
        clickResetButton();
        Utils.waitForMilliseconds(driver, 500);
        return exists;
    }

    public boolean deleteUser(String userName) {
        WebElement deleteButton = driver.findElement(deleteButtonSelector);

        deleteButton.click();
        Utils.waitForMilliseconds(driver, 500);
        driver.switchTo().alert().accept();
        return true;
    }

    public void performSearh(String query) {
        setSearchField(query);
        clickSearchButton();
        Utils.waitForMilliseconds(driver, 500);
    }

    public void setSearchField(String text) {
        driver.findElement(searchField).sendKeys(text);
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    public void clickResetButton() {
        driver.findElement(resetButton).click();
    }

    public void clickCreateUserButton() {
        driver.findElement(createUserButton).click();
    }


}
