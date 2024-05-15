package com.course.selenium;

import com.course.selenium.helpers.Browser;
import com.course.selenium.pages.AuthPage;
import com.course.selenium.pages.CreateAccountPage;
import com.course.selenium.pages.HomePage;
import com.course.selenium.pages.MyAccountPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static com.course.selenium.helpers.Helpers.getRandomEmail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountRegistrationSteps {

    private final WebDriver driver = Browser.getDriver();
    private AuthPage authPage;
    private CreateAccountPage createAccountPage;
    private MyAccountPage myAccountPage;



    @Given("the user is on the authentication page")
    public void theUserIsOnTheAuthenticationPage() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignInLink();
        authPage = new AuthPage(driver);
    }

    @When("the user types an existing email the create account input")
    public void theUserTypesAnExistingEmailTheCreateAccountInput() {
        authPage.typeEmailIntoCreateAccount("foo@bar.com");
        authPage.clickCreateAccountButton();
    }

    @When("the user types a random email into the create account input")
    public void theUserTypesARandomEmailIntoTheCreateAccountInput() {
        authPage.typeEmailIntoCreateAccount(getRandomEmail());
    }

    @And("the user clicks the create an account button")
    public void theUserClicksTheCreateAnAccountButton() {
        authPage.clickCreateAccountButton();
    }


    @And("the user fills the required fields on the create account form")
    public void theUserFillsTheRequiredFieldsOnTheCreateAccountForm() {
        createAccountPage = new CreateAccountPage(driver);
        createAccountPage.typeFirstName("John");
        createAccountPage.typeLastName("Doe");
        createAccountPage.typePassword("passwd");
    }

    @And("the user fills the required fields: {string} {string} and {string} on the account form")
    public void theUserFillsTheRequiredFieldsAndOnTheAccountForm(String firstName, String lastName, String password) {
        createAccountPage = new CreateAccountPage(driver);
        createAccountPage.typeFirstName(firstName);
        createAccountPage.typeLastName(lastName);
        createAccountPage.typePassword(password);
    }

    @And("the user clicks the register button")
    public void theUserClicksTheRegisterButton() {
        createAccountPage.clickRegisterButton();
    }

    @Then("the page should display error {string}")
    public void thePageShouldDisplayError(String errorMessage) {
        assertTrue(authPage.isErrorMessageShown());
        assertEquals(errorMessage, authPage.getErrorMessage());
    }

    @Then("the user should be logged in on their account page")
    public void theUserShouldBeLoggedInOnTheirAccountPage() {
        assertTrue(driver.getCurrentUrl().contains("my-account"));
        myAccountPage = new MyAccountPage(driver);
    }

    @And("the account page should display welcome message {string}")
    public void theAccountPageShouldDisplayWelcomeMessage(String successMessage) {
        assertTrue(myAccountPage.isSuccessMessageShown());
        assertEquals(successMessage, myAccountPage.getSuccessMessage());
    }


}



