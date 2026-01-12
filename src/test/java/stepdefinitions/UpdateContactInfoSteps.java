package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.UpdateContactInfoPage;
import utils.DriverFactory;
import java.util.Map;

public class UpdateContactInfoSteps {

    // ড্রাইভার একবারই কল করা ভালো
    WebDriver driver = DriverFactory.getDriver();
    UpdateContactInfoPage updateContactInfoPage = new UpdateContactInfoPage(driver);

    @When("user navigates to Update Contact Info page")
    public void user_navigates_to_update_contact_info_page() {
        updateContactInfoPage.openUpdateContactInfoPage();
    }

    @When("user updates contact info with:")
    public void user_updates_contact_info_with(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        updateContactInfoPage.enterContactInfo(data);
    }

    @When("user clicks Update")
    public void user_clicks_update() {
        updateContactInfoPage.clickUpdate();
    }

    @Then("contact info should be updated successfully")
    public void contact_info_should_be_updated_successfully() {
        // Assertion ফেইল করলে মেসেজ দেখাবে যা ডিবাগিং সহজ করবে
        boolean success = updateContactInfoPage.isUpdateSuccessful();
        Assert.assertTrue(success, "Update Profile success message was not displayed!");
        
        // গুরুত্বপূর্ণ: এখান থেকে quitDriver() সরিয়ে দিন। এটি Hooks-এ থাকবে।
    }
}