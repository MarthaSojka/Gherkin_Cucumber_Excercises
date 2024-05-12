package com.course.selenium.pages;

import com.course.selenium.fragments.NavBar;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.or;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class MyAccountPage {

    public NavBar navBar;
    private final WebDriverWait wait;
    private WebDriver driver;

    @FindBy(css = ".icon-building")
    private WebElement myAddress;

    @FindBy(css = "//span[contains(text(), 'Add a new address')]")
    private WebElement addNewAddress;


    public MyAccountPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.navBar = new NavBar(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isSuccessMessageShown() {
        List<WebElement> elements = driver.findElements(By.cssSelector(".alert-success"));
        return !elements.isEmpty();
    }

    public String getSuccessMessage() {
        WebElement alert = driver.findElement(By.cssSelector(".alert-success"));
        return alert.getText().strip();
    }

    public void clickMyAddress() {
        myAddress.click();
        wait.ignoring(StaleElementReferenceException.class)
                .until(
                        or(
                                presenceOfElementLocated(By.cssSelector(".alert.alert-danger")),
                                presenceOfElementLocated(By.xpath("//strong[contains(text(), 'Your addresses are listed below.')]"))
                        )
                );
    }

    public void clickAddNewAddress() {
        addNewAddress.click();
    }

}
