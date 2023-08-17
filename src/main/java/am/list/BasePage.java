package am.list;

import am.list.exceptions.InvalidInput;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Locale;

public abstract class BasePage {
    WebDriver driver;
    final static String BASE_PAGE_URL = "https://www.list.am";
    protected String endPoint;

    String footerLink = "//div[@id='pfooter']//div[@class='r']/a[text()='%s']";

    public BasePage(WebDriver driver, String endPoint) {
        this.driver = driver;
        this.endPoint = endPoint;
    }

    public void open(String link) {
        driver.get(link);
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

    public void openPage() {
        driver.get(BASE_PAGE_URL + endPoint);
    }

    public void openHelp() {
        driver.findElement(By.xpath(footerLink.formatted("Help"))).click();
    }

    public void openContactUs() {
        driver.findElement(By.xpath(footerLink.formatted("ContactUs"))).click();
    }

    public void openTermsOfService() {
        driver.findElement(By.xpath(footerLink.formatted("TermsOfService"))).click();
    }

}

