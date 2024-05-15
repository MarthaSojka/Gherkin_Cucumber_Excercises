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

public class NewAddressForm {
    @FindBy(id = "address1")
    private WebElement addressInput;

    @FindBy (id = "postcode")
    private WebElement postcodeInput;

    @FindBy (id = "city")
    private WebElement cityInput;

    @FindBy (id = "phone")
    private WebElement phoneInput;

    @FindBy (id = "alias")
    private WebElement addressTitleInput;

    @FindBy (id = "submitAddress")
    private WebElement saveAddressButton;

    private final WebDriverWait wait;

    public NewAddressForm(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void fillAddressInput(String address) {
        addressInput.clear();
        addressInput.sendKeys(address);
    }

    public void fillPostcodeInput(String zip) {
        postcodeInput.clear();
        postcodeInput.sendKeys(zip);
    }

    public void fillCityInput(String city) {
        cityInput.clear();
        cityInput.sendKeys(city);
    }

    public void fillPhoneInput(String phone) {
        phoneInput.clear();
        phoneInput.sendKeys(phone);
    }

    public void fillAddressTitleInput(String alias) {
        addressTitleInput.clear();
        addressTitleInput.sendKeys(alias);
    }

    public void clickSaveAddressButton() {
        saveAddressButton.click();
        wait.ignoring(StaleElementReferenceException.class)
            .until(
                or(
                    presenceOfElementLocated(By.cssSelector(".alert-danger")),
                    urlContains("addresses")
                )
            );
    }

}
