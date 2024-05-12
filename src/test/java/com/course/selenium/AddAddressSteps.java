package com.course.selenium;

import com.course.selenium.pages.AuthPage;
import com.course.selenium.pages.HomePage;
import com.course.selenium.pages.MyAccountPage;
import com.course.selenium.pages.MyAddressPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

public class AddAddressSteps {

    private WebDriver driver;
    private MyAccountPage myAccountPage;
    private MyAddressPage myAddressPage;
    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://hotel-testlab.coderslab.pl/en/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Given("the user is on the my account page")
    public void theUserIsOnTheMyAccountPage() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignInLink();
        AuthPage authPage = new AuthPage(driver);
        authPage.typeEmailIntoLogin("test128@testing.pl");
        authPage.typePasswordIntoLogin("test1");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        authPage.clickSignInButton();
        myAccountPage = new MyAccountPage(driver);
    }

    @When("the user clicks the my addresses button")
    public void theUserClicksTheMyAddressesButton() {
        myAccountPage.clickMyAddress();
        myAddressPage = new MyAddressPage(driver);
    }

    @And("the user fills the required fields on the my address form")
    public void theUserFillsTheRequiredFieldsOnTheMyAddressForm() {
        myAddressPage.fillAddressInput("Test");
        myAddressPage.fillPostcodeInput("55555");
        myAddressPage.fillCityInput("Test");
        myAddressPage.fillPhoneInput("1254");
        myAddressPage.fillAddressTitleInput("address" + Math.random());
    }

    @And("the user clicks the add a new address button")
    public void theUserClicksTheAddANewAddressButton() {
        myAddressPage.clickSubmitAddressButton();
    }

    @Then("there is displayed a message {string}")
    public void thereIsDisplayedAMessage(String message) {
        assertTrue(myAddressPage.isMessageShown());
    }

    @And("the new address is added to the list")
    public void theNewAddressIsAddedToTheList() {
        assertTrue(myAddressPage.getNumberOfAddresses() > 0);
    }
}
