package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.BillPayPage;
import utils.DriverFactory;

import java.util.Map;

public class BillPaySteps {

    WebDriver driver;
    BillPayPage billPayPage;

    @When("user pays bill with following details")
    public void user_pays_bill(DataTable dataTable) {

        driver = DriverFactory.getDriver();
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        billPayPage = new BillPayPage(driver);
        billPayPage.openBillPay();

        billPayPage.enterPayeeDetails(
                data.get("payeeName"),
                data.get("address"),
                data.get("city"),
                data.get("state"),
                data.get("zipCode"),
                data.get("phone"),
                data.get("account"),
                data.get("verifyAcc"),
                data.get("amount")
        );

        billPayPage.submitPayment();
    }

    @Then("bill payment should be successful")
    public void bill_payment_should_be_successful() {
        Assert.assertTrue(billPayPage.isPaymentSuccessful());
        DriverFactory.quitDriver();
    }
}
