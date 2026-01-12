package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginBtn = By.xpath("//input[@value='Log In']");
    private final By logoutLink = By.linkText("Log Out");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // ওয়েট টাইম ১৫ সেকেন্ড করা হলো কারণ প্যারাব্যাংক মাঝে মাঝে স্লো থাকে
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void enterUsername(String user) {
        // এলিমেন্ট দৃশ্যমান হওয়ার পাশাপাশি সেটি এনাবলড আছে কিনা চেক করা
        WebElement userField = wait.until(ExpectedConditions.elementToBeClickable(usernameField));
        userField.clear();
        userField.sendKeys(user);
    }

    public void enterPassword(String pass) {
        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passField.clear();
        passField.sendKeys(pass);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }

    public boolean isLogoutDisplayed() {
        try {
            // লগইন সফল হওয়ার পর পেজ রিফ্রেশ হতে সময় নেয়, তাই ওয়েট জরুরি
            return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}