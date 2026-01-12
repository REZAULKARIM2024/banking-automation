package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.FundTransferPage;
import utils.DriverFactory;

public class FundTransferSteps {
    FundTransferPage fundTransferPage = new FundTransferPage(DriverFactory.getDriver());

    @When("user navigates to Fund Transfer page")
    public void navigate() {
        fundTransferPage.openFundTransfer();
    }

    @And("user transfers amount {string} from account {string} to account {string}")
    public void performTransfer(String amount, String from, String to) {
        fundTransferPage.transferFunds(from, to, amount);
    }

    @Then("transfer should be successful")
    public void verifyTransfer() {
        Assert.assertTrue(fundTransferPage.isTransferSuccessful(), "Fund transfer failed or success message not found!");
    }
}