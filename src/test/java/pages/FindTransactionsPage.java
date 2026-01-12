package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FindTransactionsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By findTransactionsLink = By.linkText("Find Transactions");
    // XPath টি আরও জেনেরিক করা হয়েছে যাতে ডট (.) এর সমস্যা না হয়
    private final By fromDateInput = By.xpath("//input[contains(@id, 'fromDate')]");
    private final By toDateInput = By.xpath("//input[contains(@id, 'toDate')]");
    private final By findByDateRangeButton = By.xpath("(//button[@type='submit'])[3]");
    private final By transactionTable = By.id("transactionTable");

    public FindTransactionsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void openFindTransactionsPage() {
        // ১. লিঙ্কে ক্লিক করার আগে সেটি দৃশ্যমান কিনা নিশ্চিত করা
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(findTransactionsLink));
        link.click();
        
        // ২. জাভাস্ক্রিপ্ট ব্যবহার করে নিশ্চিত করা যে পেজটি পুরোপুরি লোড হয়েছে
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
        
        // ৩. ইনপুট ফিল্ডটি ক্লিক করার উপযোগী হওয়া পর্যন্ত অপেক্ষা
        wait.until(ExpectedConditions.elementToBeClickable(fromDateInput));
    }

    public void enterDateRange(String fromDate, String toDate) {
        WebElement from = driver.findElement(fromDateInput);
        from.clear();
        from.sendKeys(fromDate);

        WebElement to = driver.findElement(toDateInput);
        to.clear();
        to.sendKeys(toDate);
    }

    public void clickFindTransactions() {
        // স্ক্রল করে বাটনটি সামনে আনা (যদি প্রয়োজন হয়)
        WebElement btn = driver.findElement(findByDateRangeButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        btn.click();
    }

    public boolean isTransactionTableVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(transactionTable)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}