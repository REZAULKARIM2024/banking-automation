package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.OpenNewAccountPage;
import utils.DriverFactory;

public class OpenNewAccountSteps {

    WebDriver driver;
    OpenNewAccountPage openNewAccountPage;

    @When("user navigates to Open New Account page")
    public void user_navigates_to_open_new_account_page() {
        driver = DriverFactory.getDriver();
        openNewAccountPage = new OpenNewAccountPage(driver);
        openNewAccountPage.openOpenNewAccountPage();
    }

    // এই মেথডটি আপনার এরর লগের 'Undefined Step' ফিক্স করবে
    @When("user selects account type {string} and existing account {string}")
    public void user_selects_account_type_and_existing_account(String type, String fromAcc) {
        openNewAccountPage.selectAccountTypeAndExistingAccount(type, fromAcc);
    }

    @When("user clicks Open New Account")
    public void user_clicks_open_new_account() {
        openNewAccountPage.clickOpenNewAccount();
    }

    @Then("new account should be created successfully")
    public void new_account_should_be_created_successfully() {
        Assert.assertTrue(openNewAccountPage.isAccountCreated(), "New account creation failed!");
    }
}