package com.course.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class CreateAccountPage {

    @FindBy(id = "customer_firstname")
    private WebElement firstNameInput;

    @FindBy(id = "customer_lastname")
    private WebElement lastNameInput;

    @FindBy(id = "passwd")
    private WebElement passwordInput;

    @FindBy(id = "submitAccount")
    private WebElement registerButton;


    private final WebDriverWait wait;

    public CreateAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void typeFirstName(String name) {
        firstNameInput.clear();
        firstNameInput.sendKeys(name);
    }

    public void typeLastName(String surname) {
        lastNameInput.clear();
        lastNameInput.sendKeys(surname);
    }

    public void typePassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickRegisterButton() {
        registerButton.click();
        wait.ignoring(StaleElementReferenceException.class)
            .until(
                or(
                    presenceOfElementLocated(By.cssSelector(".alert-danger")),
                    urlContains("my-account")
                )
            );
    }
}
