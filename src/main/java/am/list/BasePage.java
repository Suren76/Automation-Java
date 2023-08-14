package am.list;

import am.list.exceptions.InvalidInput;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Locale;

public class BasePage {
    WebDriver driver;
    final static String HOME_PAGE_URL = "https://www.list.am";
    String footerLink = "//div[@id='pfooter']//div[@class='r']/a[text()='%s']";

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait shortWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void changeLanguage(String lang) {
        lang = lang.toLowerCase(Locale.ROOT).substring(0, 2);
        if (!(lang.equals("en") || lang.equals("ru") || lang.equals("am"))) {
            throw new InvalidInput("Invalid input: " + lang);
        }

        WebElement langToggle = driver.findElement(By.xpath("//div[@id='lbar']"));
        if (langToggle.getAttribute("class").equals(lang)) return;

        langToggle.click();

        shortWait().until(ExpectedConditions.attributeToBe(By.id("lmenu"), "style", "display: block;"));

        driver.findElement(By.xpath("//div[@id='lbar']//a[contains(@href, '%s')]".formatted(lang))).click();
    }

    void openHelp() {
        driver.findElement(By.xpath(footerLink.formatted("Help"))).click();
    }

    void openContactUs() {
        driver.findElement(By.xpath(footerLink.formatted("ContactUs"))).click();
    }

    void openTermsOfService() {
        driver.findElement(By.xpath(footerLink.formatted("TermsOfService"))).click();
    }

}

