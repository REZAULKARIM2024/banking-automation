package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.FindTransactionsPage;
import utils.DriverFactory;

public class FindTransactionsSteps {

    private WebDriver driver;
    private FindTransactionsPage findTransactionsPage;

    private void setup() {
        if (driver == null) {
            driver = DriverFactory.getDriver();
            findTransactionsPage = new FindTransactionsPage(driver);
        }
    }

    @When("user navigates to Find Transactions page")
    public void user_navigates_to_find_transactions_page() {
        setup();
        findTransactionsPage.openFindTransactionsPage();
    }

    @And("user enters date range from {string} to {string}")
    public void user_enters_date_range(String fromDate, String toDate) {
        setup();
        findTransactionsPage.enterDateRange(fromDate, toDate);
    }

    @And("user clicks the Find Transactions button for date range")
    public void user_clicks_find() {
        setup();
        findTransactionsPage.clickFindTransactions();
    }

    @Then("a table with transaction history should be displayed")
    public void verify_table() {
        setup();
        Assert.assertTrue(findTransactionsPage.isTransactionTableVisible(), "Error: Transaction table not visible!");
    }
}