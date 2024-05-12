package com.course.selenium.fragments;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavBar {

    @FindBy(id = "header_logo")
    private WebElement headerLogo;

    public NavBar(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickLogo() {
        headerLogo.click();
    }
}
