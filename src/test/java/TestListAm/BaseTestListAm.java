package TestListAm;

import BaseTest.BaseTest;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTestListAm extends BaseTest {
    protected boolean isClickable(WebElement elem) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(elem));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}