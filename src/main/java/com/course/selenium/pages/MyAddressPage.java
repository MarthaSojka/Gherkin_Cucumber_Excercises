package com.course.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.or;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class MyAddressPage {

    @FindBy (id = "address1")
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
    private WebElement submitAddressButton;

    @FindBy (xpath = "//strong[contains(text(), 'Your addresses are listed below.')]")
    private WebElement listedAddresses;

    @FindBy (css = ".box")
    private List<WebElement> addresses;

    private final WebDriverWait wait;

    private WebDriver driver;

    public MyAddressPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void fillAddressInput(String address) {
        addressInput.sendKeys(address);
    }

    public void fillPostcodeInput(String zip) {
        postcodeInput.sendKeys(zip);
    }

    public void fillCityInput(String city) {
        cityInput.sendKeys(city);
    }

    public void fillPhoneInput(String phone) {
        phoneInput.sendKeys(phone);
    }

    public void fillAddressTitleInput(String alias) {
        addressTitleInput.sendKeys(alias);
    }

    public void clickSubmitAddressButton() {
        submitAddressButton.click();
        wait.ignoring(StaleElementReferenceException.class)
                .until(
                        or(
                                presenceOfElementLocated(By.cssSelector(".alert.alert-danger")),
                                presenceOfElementLocated(By.xpath("//strong[contains(text(), 'Your addresses are listed below.')]"))
                        )
                );
    }

    public boolean isMessageShown() {
        List<WebElement> elements = driver.findElements(By.xpath("//*[contains(text(), 'Your addresses are listed below.')]"));
        return !elements.isEmpty();
    }

    public String getMessage() {
        WebElement message = driver.findElement(By.xpath("//*[contains(text(), 'Your addresses are listed below.')]"));
        return message.getText().strip();
    }

    public int getNumberOfAddresses() {
        return addresses.size();
    }

}
