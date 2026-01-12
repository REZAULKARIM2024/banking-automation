package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Map;

public class UpdateContactInfoPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators - Parabank এর ID গুলো সাধারণত 'customer.' দিয়ে শুরু হয়
    private final By firstNameInput = By.id("customer.firstName");
    private final By lastNameInput = By.id("customer.lastName");
    private final By addressInput = By.id("customer.address.street");
    private final By cityInput = By.id("customer.address.city");
    private final By stateInput = By.id("customer.address.state");
    private final By zipInput = By.id("customer.address.zipCode");
    private final By phoneInput = By.id("customer.phoneNumber");
    private final By updateButton = By.xpath("//input[@value='Update Profile']");
    
    // Success Message-এর জন্য আরও ডাইনামিক XPath
    private final By successMessage = By.xpath("//h1[text()='Update Profile']/following-sibling::p[contains(text(),'Profile updated')]");

    public UpdateContactInfoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // ১০ সেকেন্ড থেকে বাড়িয়ে ১৫ করা হয়েছে
    }

    public void openUpdateContactInfoPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Update Contact Info"))).click();
        // নিশ্চিত করুন যে পেজটি লোড হয়েছে এবং অন্তত একটি ফিল্ড দৃশ্যমান
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
    }

    // ইনপুট ফিল্ড পরিষ্কার করার জন্য একটি হেল্পার মেথড
    private void clearAndSendKeys(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(value);
    }

    public void enterContactInfo(Map<String, String> data) {
        clearAndSendKeys(firstNameInput, data.get("firstName"));
        clearAndSendKeys(lastNameInput, data.get("lastName"));
        clearAndSendKeys(addressInput, data.get("address"));
        clearAndSendKeys(cityInput, data.get("city"));
        clearAndSendKeys(stateInput, data.get("state"));
        clearAndSendKeys(zipInput, data.get("zipCode"));
        clearAndSendKeys(phoneInput, data.get("phone"));
    }

    public void clickUpdate() {
        wait.until(ExpectedConditions.elementToBeClickable(updateButton)).click();
    }

    public boolean isUpdateSuccessful() {
        try {
            // ১. ১ সেকেন্ড অপেক্ষা করুন যাতে ডাটা সাবমিট হয়
            Thread.sleep(1000);
            
            // ২. মেসেজটি খুঁজুন (এটি সাধারণত একটি <p> ট্যাগে থাকে)
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Profile Updated')] | //*[contains(text(), 'successfully')]")));
            
            return message.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}