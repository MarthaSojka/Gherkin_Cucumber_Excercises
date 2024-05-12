package com.course.selenium.fragments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectHotel {

    @FindBy(id = "hotel_cat_name")
    private WebElement dropdownArrow;

    private final WebDriver driver;

    public SelectHotel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectHotelCategoryByName(String categoryName) {
        dropdownArrow.click();
        String expression = String.format("//li[text()='%s']", categoryName);
        WebElement category = driver.findElement(By.xpath(expression));
        category.click();
    }
}
