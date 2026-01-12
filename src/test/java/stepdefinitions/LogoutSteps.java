package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.LogoutPage;
import utils.DriverFactory;

public class LogoutSteps {

    WebDriver driver;
    LogoutPage logoutPage;

    @When("user clicks logout")
    public void user_clicks_logout() {
        driver = DriverFactory.getDriver();
        logoutPage = new LogoutPage(driver);
        logoutPage.clickLogout();
    }

    @Then("user should be logged out successfully")
    public void user_should_be_logged_out_successfully() {
        Assert.assertTrue(logoutPage.isLoggedOut());
        DriverFactory.quitDriver();
    }
}
