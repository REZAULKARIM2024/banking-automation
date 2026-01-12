package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.AccountsOverviewPage;
import utils.DriverFactory;

public class AccountsOverviewSteps {

    private WebDriver driver;
    private AccountsOverviewPage accountsOverviewPage;

    // মেথডগুলো রান হওয়ার আগে ড্রাইভার এবং পেজ অবজেক্ট সেটআপ করার জন্য একটি হেল্পার মেথড
    private void setup() {
        if (driver == null) {
            driver = DriverFactory.getDriver();
            accountsOverviewPage = new AccountsOverviewPage(driver);
        }
    }

    @When("user navigates to Accounts Overview page")
    public void user_navigates_to_accounts_overview_page() {
        setup(); // নিশ্চিত করে ড্রাইভার এবং পেজ অবজেক্ট রেডি
        accountsOverviewPage.openAccountsOverviewPage();
    }

    @Then("user should see a list of all accounts with balances")
    public void user_should_see_a_list_of_all_accounts_with_balances() {
        setup();
        
        // ডাইনামিক ডাটা লোড হওয়ার জন্য এখানে আমরা পেজ অবজেক্টের বুলিয়ান চেকটি ব্যবহার করছি
        boolean isDisplayed = accountsOverviewPage.isAccountsListDisplayed();
        
        // Assertion: ব্যর্থ হলে সঠিক কারণ কনসোলে দেখাবে
        Assert.assertTrue(isDisplayed, 
            "FAILED: Accounts overview table load হতে ব্যর্থ হয়েছে। সম্ভবত ডাটাবেজে কোনো অ্যাকাউন্ট নেই অথবা সার্ভার এরর (Internal Error) ঘটেছে।");
    }
}