package com.course.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RoomsPage {

    @FindBy(css = ".room_cont")
    private List<WebElement> rooms;

    public RoomsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public int getNumberOfRooms() {
        return rooms.size();
    }

}
