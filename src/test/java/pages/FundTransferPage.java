package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FundTransferPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By transferFundsLink = By.linkText("Transfer Funds");
    private final By amountField = By.id("amount");
    private final By fromAccountDropdown = By.id("fromAccountId");
    private final By toAccountDropdown = By.id("toAccountId");
    private final By transferBtn = By.xpath("//input[@value='Transfer']");
    private final By successMsg = By.xpath("//div[@id='showResult']//h1[contains(text(),'Transfer Complete')]");

    public FundTransferPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void openFundTransfer() {
        wait.until(ExpectedConditions.elementToBeClickable(transferFundsLink)).click();
        wait.until(ExpectedConditions.urlContains("transfer.htm"));
    }

    public void transferFunds(String fromAcc, String toAcc, String amt) {
        // ১. অ্যামাউন্ট ইনপুট
        WebElement amtInput = wait.until(ExpectedConditions.visibilityOfElementLocated(amountField));
        amtInput.clear();
        amtInput.sendKeys(amt);

        // ২. AJAX ড্রপডাউন সিঙ্ক্রোনাইজেশন: অন্তত একটি অপশন আসা পর্যন্ত অপেক্ষা
        wait.until(d -> new Select(d.findElement(fromAccountDropdown)).getOptions().size() > 0);
        
        // From Account সিলেক্ট (এক্সেপশন হ্যান্ডেলিং সহ)
        try {
            new Select(driver.findElement(fromAccountDropdown)).selectByVisibleText(fromAcc);
        } catch (Exception e) {
            new Select(driver.findElement(fromAccountDropdown)).selectByIndex(0);
        }

        // ৩. To Account সিলেক্ট (প্যারাব্যাংক অনেক সময় From সিলেক্টের পর এটি রিফ্রেশ করে)
        try { Thread.sleep(1000); } catch (InterruptedException ie) {}
        wait.until(d -> new Select(d.findElement(toAccountDropdown)).getOptions().size() > 0);
        
        try {
            new Select(driver.findElement(toAccountDropdown)).selectByVisibleText(toAcc);
        } catch (Exception e) {
            new Select(driver.findElement(toAccountDropdown)).selectByIndex(0);
        }

        // ৪. ট্রান্সফার বাটন ক্লিক
        wait.until(ExpectedConditions.elementToBeClickable(transferBtn)).click();
    }

    public boolean isTransferSuccessful() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}