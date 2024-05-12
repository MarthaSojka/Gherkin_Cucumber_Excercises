package com.course.selenium.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class AuthPage {

    @FindBy(id = "create_account_error")
    private WebElement createAccountAlert;

    @FindBy(id = "email_create")
    private WebElement createAccountEmailInput;

    @FindBy(id = "SubmitCreate")
    private WebElement createAccountButton;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "passwd")
    private WebElement passwordInput;

    @FindBy(id = "SubmitLogin")
    private WebElement signInButton;

    private WebDriverWait wait;

    public AuthPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void typeEmailIntoCreateAccount(String email) {
        createAccountEmailInput.clear();
        createAccountEmailInput.sendKeys(email);
    }

    public void clickCreateAccountButton() {
        createAccountButton.click();
        wait.ignoring(StaleElementReferenceException.class)
                .until(
                        or(
                                visibilityOf(createAccountAlert),
                                urlContains("#account-creation")
                        )
                );
    }

    public boolean isErrorMessageShown() {
        return createAccountAlert.isDisplayed();
    }

    public String getErrorMessage() {
        return createAccountAlert.getText();
    }

    public void typeEmailIntoLogin(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void typePasswordIntoLogin(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickSignInButton() {
        createAccountButton.click();
        wait.ignoring(StaleElementReferenceException.class)
                .until(
                        or(
                                urlContains("my-account")
                        )
                );
    }



}