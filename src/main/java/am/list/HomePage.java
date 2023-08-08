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

        driver.findElement(By.xpath("//div[@id='lbar']//a[contains(@href, '" + lang + "')]")).click();
    }

    public ResultPage selectCategory(String categoryMenu, String subCategory) {
        WebElement globalCategoryItem = driver.findElement(By.xpath("//div[@id='menu']/div/div/a[text()='" + categoryMenu + "']/.."));
        new Actions(driver).moveToElement(globalCategoryItem).build().perform();

        WebElement categoryToSelect = globalCategoryItem.findElement(By.xpath(".//a[text()='" + subCategory + "']"));
        shortWait().until(ExpectedConditions.elementToBeClickable(By.xpath(".//a[text()='" + subCategory + "']")));

        categoryToSelect.click();
        return new ResultPage(driver);
    }
}

