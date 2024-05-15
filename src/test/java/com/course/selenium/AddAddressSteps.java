package com.course.selenium;

import com.course.selenium.helpers.Browser;
import com.course.selenium.pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static com.course.selenium.helpers.Helpers.getRandomAlias;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddAddressSteps {

    private final WebDriver driver = Browser.getDriver();
    private MyAccountPage myAccountPage;
    private MyAddressPage myAddressPage;
    private NewAddressForm newAddressForm;
    private String addressAlias;

    @Given("the user is on My account page")
    public void theUserIsOnMyAccountPage() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignInLink();
        AuthPage authPage = new AuthPage(driver);
        authPage.typeEmailIntoLogin("test128@testing.pl");
        authPage.typePasswordIntoLogin("test1");

        authPage.clickSignInButton();
        myAccountPage = new MyAccountPage(driver);
    }

    @When("the user clicks My addresses button")
    public void theUserClicksMyAddressesButton() {
        myAccountPage.clickMyAddress();
        myAddressPage = new MyAddressPage(driver);
        myAddressPage.clickAddNewAddressButton();
        newAddressForm = new NewAddressForm(driver);
    }

    @And("the user fills required fields on my address form")
    public void theUserFillsRequiredFieldsOnMyAddressForm() {
        addressAlias = getRandomAlias();

        newAddressForm.fillAddressInput("Krótka 32");
        newAddressForm.fillPostcodeInput("55-555");
        newAddressForm.fillCityInput("Warszawa");
        newAddressForm.fillPhoneInput("555-555-555");
        newAddressForm.fillAddressTitleInput(addressAlias);
    }

    @And("the user clicks Add a new address button")
    public void theUserClicksAddANewAddressButton() {
        newAddressForm.clickSaveAddressButton();
        myAddressPage = new MyAddressPage(driver);
    }
/*
    @Then("there is displayed a message {string}")
    public void thereIsDisplayedAMessage(String message) {
        assertTrue(myAddressPage.isMessageShown());
    }

    @And("the new address is added to the list")
    public void theNewAddressIsAddedToTheList() {
        assertTrue(myAddressPage.getNumberOfAddresses() > 0);
    }
*/

    @Then("my addresses page should include new address")
    public void myAddressesPageShouldIncludeNewAddress() {
        Map<String, String> addressByAlias = myAddressPage.getAddressesByAlias();
        String key = addressAlias.toUpperCase();
        assertTrue(addressByAlias.containsKey(key));
        assertEquals("Test Testing Krótka 32 55-555 Warszawa Poland 555-555-555", addressByAlias.get(key));
    }

}
