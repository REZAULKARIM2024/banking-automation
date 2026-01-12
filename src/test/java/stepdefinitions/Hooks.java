package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;
import java.time.Duration;

public class Hooks {

    @Before
    public void setUp() {
        WebDriver driver = DriverFactory.getDriver();
        if (driver != null) {
            // ১. ক্যাশ এবং কুকি ক্লিন করা (Internal Error ফিক্স করতে সাহায্য করে)
            driver.manage().deleteAllCookies();
            
            // ২. উইন্ডো ম্যাক্সিমাইজ করা
            driver.manage().window().maximize();
            
            // ৩. ইমপ্লিসিট ওয়েট সেট করা (এলিমেন্ট না পাওয়া পর্যন্ত অপেক্ষা করবে)
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            // ৪. পেজ লোড টাইম আউট সেট করা
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();
        
        if (driver != null) {
            // টেস্ট ফেইল করলে স্ক্রিনশট নেওয়া
            if (scenario.isFailed()) {
                try {
                    final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "Failed_Screenshot_" + scenario.getName());
                } catch (Exception e) {
                    System.err.println("Screenshot capture failed: " + e.getMessage());
                }
            }
            
            // ৫. ড্রাইভার কুইট করার আগে সেশন ক্লিন নিশ্চিত করা
            try {
                DriverFactory.quitDriver();
            } catch (Exception e) {
                System.err.println("Error closing driver: " + e.getMessage());
            }
        }
    }
}