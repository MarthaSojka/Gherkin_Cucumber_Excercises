package com.course.selenium.pages;

import com.course.selenium.fragments.AddressPanel;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.or;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class MyAddressPage {

    @FindBy(css = "a[title='Add an address']")
    private WebElement addNewAddressButton;

    @FindBy (css = ".address")
    private List<WebElement> addresses;

    public MyAddressPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickAddNewAddressButton() {
        addNewAddressButton.click();
    }

    public Map<String, String> getAddressesByAlias() {
        Map<String, String> addressesByAlias = new HashMap<>();
        for (WebElement address : addresses) {
            AddressPanel addressPanel = new AddressPanel(address);
            addressesByAlias.put(addressPanel.header, addressPanel.content);
        }
        return addressesByAlias;
    }
/*
    public String getMessage() {
        WebElement message = driver.findElement(By.xpath("//*[contains(text(), 'Your addresses are listed below.')]"));
        return message.getText().strip();
    }

    public int getNumberOfAddresses() {
        return addresses.size();
    }
*/
}
