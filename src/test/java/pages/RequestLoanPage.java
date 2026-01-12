package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RequestLoanPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By requestLoanLink = By.linkText("Request Loan");
    private final By loanAmountInput = By.id("amount");
    private final By downPaymentInput = By.id("downPayment");
    private final By applyButton = By.xpath("//input[@value='Apply Now']");
    // সাকসেস মেসেজ অনেক সময় dynamic হয়, তাই contains ব্যবহার করা নিরাপদ
    private final By approvalMessage = By.xpath("//div[@id='requestLoanResult']//h1[contains(text(),'Loan Request Processed')]");

    public RequestLoanPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void openRequestLoanPage() {
        wait.until(ExpectedConditions.elementToBeClickable(requestLoanLink)).click();
        wait.until(ExpectedConditions.urlContains("requestloan.htm"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loanAmountInput));
    }

    private void typeData(By locator, String data) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.clear();
        // অনেক সময় value clear হয় না, তাই সরাসরি কি-বোর্ড অ্যাকশন ব্যবহার করা ভালো
        if(!element.getAttribute("value").isEmpty()) {
            element.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
            element.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);
        }
        element.sendKeys(data);
    }

    public void enterLoanDetails(String amount, String downPayment) {
        typeData(loanAmountInput, amount);
        typeData(downPaymentInput, downPayment);
    }

    public void clickApply() {
        wait.until(ExpectedConditions.elementToBeClickable(applyButton)).click();
    }

    public boolean isLoanApproved() {
        try {
            // লোন প্রসেস হতে ৫-৭ সেকেন্ড সময় নিতে পারে
            return wait.until(ExpectedConditions.visibilityOfElementLocated(approvalMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}