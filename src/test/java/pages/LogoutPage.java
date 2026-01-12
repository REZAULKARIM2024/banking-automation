package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {

    WebDriver driver;

    By logoutLink = By.linkText("Log Out");
    By loginPageHeader = By.xpath("//h2[text()='Customer Login']");

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLogout() {
        driver.findElement(logoutLink).click();
    }

    public boolean isLoggedOut() {
        return driver.findElement(loginPageHeader).isDisplayed();
    }
}
