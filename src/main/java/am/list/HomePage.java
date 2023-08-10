package am.list;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Locale;

public class HomePage extends BasePage {
    final static String HOME_PAGE_URL = "https://www.list.am";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(HOME_PAGE_URL);
    }

}

