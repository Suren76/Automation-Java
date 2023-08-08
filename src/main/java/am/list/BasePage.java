package am.list;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait shortWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(3));
    }
}

