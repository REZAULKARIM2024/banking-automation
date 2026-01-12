package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.LoginPage;
import utils.DriverFactory;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;

    // ==========================================
    // Step-by-step login (login.feature)
    // ==========================================

    @Given("user is on Parabank login page")
    public void user_is_on_parabank_login_page() {
        driver = DriverFactory.getDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        loginPage = new LoginPage(driver);
    }

    @When("user enters username {string}")
    public void user_enters_username(String username) {
        loginPage.enterUsername(username);
    }

    @When("user enters password {string}")
    public void user_enters_password(String password) {
        loginPage.enterPassword(password);
    }

    @When("user clicks login")
    public void user_clicks_login() {
        loginPage.clickLogin();
    }

    @Then("user should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        // LoginPage-এ থাকা Explicit Wait ব্যবহার করে চেক করবে
        boolean isLogged = loginPage.isLogoutDisplayed();
        Assert.assertTrue(isLogged, "Login failed: Logout link is not visible.");
    }

    // ==========================================
    // Reusable direct login (Background or Other features)
    // ==========================================

    @Given("user is logged into Parabank")
    public void user_is_logged_into_parabank() {
        driver = DriverFactory.getDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        loginPage = new LoginPage(driver);
        loginPage.enterUsername("john"); // আপনার ভ্যালিড ইউজারনেম দিন
        loginPage.enterPassword("demo"); // আপনার ভ্যালিড পাসওয়ার্ড দিন
        loginPage.clickLogin();

        // নিশ্চিত করা হচ্ছে যে পরবর্তী স্টেপ রান করার আগে লগইন সম্পন্ন হয়েছে
        Assert.assertTrue(loginPage.isLogoutDisplayed(), "Pre-condition failed: User could not log in.");
    }
}