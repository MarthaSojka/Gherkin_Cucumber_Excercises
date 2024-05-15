package com.course.selenium.pages;

import com.course.selenium.fragments.SelectHotel;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class HomePage {

    public SelectHotel selectHotel;

    @FindBy(css = ".hide_xs")
    private WebElement signInLink;

    @FindBy(id = "hotel_location")
    private WebElement hotelLocationInput;

    @FindBy(id = "hotel_cat_id")
    private WebElement selectHotelCategory;

    @FindBy(id = "check_in_time")
    private WebElement checkInDateInput;

    @FindBy(id = "check_out_time")
    private WebElement checkOutDateInput;

    @FindBy(id = "search_room_submit")
    private WebElement searchNowButton;

    private final WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.selectHotel = new SelectHotel(driver);
        // Metoda przepatrzy instancje i wybierze selektor i przekaże go do drivera
        PageFactory.initElements(driver, this);
    }

    public void clickSignInLink() {
        signInLink.click();
    }

    public void fillHotelLocation(String location) {
        hotelLocationInput.clear();
        hotelLocationInput.sendKeys(location);
    }

    public void fillCheckInDate(String date) {
        checkInDateInput.clear();
        checkInDateInput.sendKeys(date);
    }

    public void fillCheckOutDate(String date) {
        checkOutDateInput.clear();
        checkOutDateInput.sendKeys(date);
    }

    public void clickSearchNowButton() {
        searchNowButton.click();
        wait.ignoring(StaleElementReferenceException.class)
            .until(
                or(
                    presenceOfElementLocated(By.cssSelector(".error_border")),
                    presenceOfElementLocated(By.id("filter_search_block"))
                )
            );
   }
}
