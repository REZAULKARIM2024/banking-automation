package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.RequestLoanPage;
import utils.DriverFactory;

public class RequestLoanSteps {

    WebDriver driver;
    RequestLoanPage requestLoanPage;

    // ড্রাইভার এবং পেজ অবজেক্ট ইনিশিয়ালাইজ করার হেল্পার মেথড
    private void setup() {
        if (driver == null) {
            driver = DriverFactory.getDriver();
            requestLoanPage = new RequestLoanPage(driver);
        }
    }

    @When("user navigates to Request Loan page")
    public void user_navigates_to_request_loan_page() {
        setup();
        requestLoanPage.openRequestLoanPage();
    }

    // ফিক্সড: পেজ ক্লাসের enterLoanDetails মেথডটি কল করা হয়েছে
    @When("user enters loan amount {string} and down payment {string}")
    public void user_enters_loan_amount_and_down_payment(String amount, String downPayment) {
        setup();
        requestLoanPage.enterLoanDetails(amount, downPayment);
    }

    @And("user clicks Apply Now")
    public void user_clicks_apply_now() {
        setup();
        requestLoanPage.clickApply();
    }

    @Then("loan request should be approved")
    public void loan_request_should_be_approved() {
        setup();
        boolean isApproved = requestLoanPage.isLoanApproved();
        Assert.assertTrue(isApproved, "FAILED: Loan request approved হয়নি। এটি Denied হতে পারে অথবা সার্ভার রেসপন্স দেয়নি।");
    }
}