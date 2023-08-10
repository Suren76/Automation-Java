package am.list;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Locale;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait shortWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void changeLanguage(String lang) {
        lang = lang.toLowerCase(Locale.ROOT).substring(0, 2);
        if (!(lang.equals("en") || lang.equals("ru") || lang.equals("am")))
            throw new InvalidInput("Invalid input: " + lang);

        WebElement langToggle = driver.findElement(By.xpath("//div[@id='lbar']"));
        if (langToggle.getAttribute("class").equals(lang)) {
            return;
        }

        langToggle.click();

        shortWait().until(ExpectedConditions.attributeToBe(By.id("lmenu"), "style", "display: block;"));

        driver.findElement(By.xpath("//div[@id='lbar']//a[contains(@href, '%s')]".formatted(lang))).click();
    }

    public ResultPage selectCategory(String categoryMenu, String subCategory) {
        WebElement globalCategoryItem = driver.findElement(By.xpath("//div[@id='menu']/div/div/a[text()='%s']/..".formatted(categoryMenu)));
        new Actions(driver).moveToElement(globalCategoryItem).build().perform();

        WebElement categoryToSelect = globalCategoryItem.findElement(By.xpath(".//a[text()='%s']".formatted(subCategory)));
        shortWait().until(ExpectedConditions.elementToBeClickable(categoryToSelect));

        categoryToSelect.click();
        return new ResultPage(driver);
    }
}

