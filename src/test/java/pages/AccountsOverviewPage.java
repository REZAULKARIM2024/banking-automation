package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class AccountsOverviewPage {
    WebDriver driver;
    WebDriverWait wait;

    private final By accountsOverviewLink = By.linkText("Accounts Overview");
    private final By accountTable = By.id("accountTable");
    // ব্যালেন্স বা অন্তত একটি ডেটা সেল উপস্থিত হওয়া পর্যন্ত অপেক্ষা করার জন্য
    private final By tableData = By.xpath("//table[@id='accountTable']//td");

    public AccountsOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void openAccountsOverviewPage() {
        wait.until(ExpectedConditions.elementToBeClickable(accountsOverviewLink)).click();
        wait.until(ExpectedConditions.urlContains("overview.htm"));
    }

    public boolean isAccountsListDisplayed() {
        try {
            // টেবিল দৃশ্যমান হওয়া পর্যন্ত অপেক্ষা
            wait.until(ExpectedConditions.visibilityOfElementLocated(accountTable));
            // অন্তত একটি ডেটা সেল (Data Cell) লোড হওয়া পর্যন্ত অপেক্ষা (Thread.sleep এর পরিবর্তে)
            wait.until(ExpectedConditions.presenceOfElementLocated(tableData));
            
            List<WebElement> rows = driver.findElements(By.xpath("//table[@id='accountTable']/tbody/tr"));
            // Total ব্যালেন্সের রো বাদ দিয়ে অন্তত ১টি অ্যাকাউন্ট রো থাকতে হবে
            return rows.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}