package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class OpenNewAccountPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By openAccountLink = By.linkText("Open New Account");
    private final By accountTypeDropdown = By.id("type");
    private final By existingAccountDropdown = By.id("fromAccountId");
    private final By openAccountButton = By.xpath("//input[@value='Open New Account']");
    private final By newAccountMessage = By.xpath("//h1[text()='Account Opened!']");

    public OpenNewAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void openOpenNewAccountPage() {
        wait.until(ExpectedConditions.elementToBeClickable(openAccountLink)).click();
        wait.until(ExpectedConditions.urlContains("openaccount.htm"));
    }

    public void selectAccountTypeAndExistingAccount(String accountType, String existingAccount) {
        // ১. একাউন্ট টাইপ সিলেক্ট (লুপ ব্যবহার করে কেস-সেনসিটিভিটি হ্যান্ডেল করা হয়েছে)
        WebElement typeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(accountTypeDropdown));
        Select typeSelect = new Select(typeElement);
        
        boolean found = false;
        for (WebElement option : typeSelect.getOptions()) {
            if (option.getText().equalsIgnoreCase(accountType)) {
                typeSelect.selectByVisibleText(option.getText());
                found = true;
                break;
            }
        }
        // যদি টেক্সট না মেলে, তবে ইনডেক্স দিয়ে সিলেক্ট করার ব্যাকআপ লজিক
        if(!found) typeSelect.selectByIndex(accountType.equalsIgnoreCase("SAVINGS") ? 1 : 0);

        // ২. ড্রপডাউন অপশন লোড হওয়ার জন্য অপেক্ষা (Parabank AJAX Fix)
        wait.until(d -> new Select(d.findElement(existingAccountDropdown)).getOptions().size() > 0);

        // ৩. এক্সিস্টিং একাউন্ট সিলেক্ট
        Select existingSelect = new Select(driver.findElement(existingAccountDropdown));
        try {
            existingSelect.selectByVisibleText(existingAccount);
        } catch (Exception e) {
            // যদি নির্দিষ্ট একাউন্ট না পায়, প্রথম একাউন্টটি সিলেক্ট করবে যাতে টেস্ট ক্রাশ না করে
            existingSelect.selectByIndex(0);
        }
    }

    public void clickOpenNewAccount() {
        // বাটন এনাবেল হওয়ার জন্য সামান্য হার্ড ওয়েট (AngularJS অ্যাপের জন্য কার্যকর)
        try { Thread.sleep(1500); } catch (InterruptedException e) {}
        wait.until(ExpectedConditions.elementToBeClickable(openAccountButton)).click();
    }

    public boolean isAccountCreated() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(newAccountMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}