package am.list;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface SelectCategoryBar {
    WebDriverWait shortWait();

    ResultPage selectCategory(String categoryMenu, String subCategory);

    default ResultPage selectCategory(WebDriver driver, String categoryMenu, String subCategory) {
        WebElement globalCategoryItem = driver.findElement(By.xpath("//div[@id='menu']/div/div/a[text()='%s']/..".formatted(categoryMenu)));
        new Actions(driver).moveToElement(globalCategoryItem).build().perform();

        WebElement categoryToSelect = globalCategoryItem.findElement(By.xpath(".//a[text()='%s']".formatted(subCategory)));
        shortWait().until(ExpectedConditions.elementToBeClickable(categoryToSelect));

        categoryToSelect.click();
        return new ResultPage(driver);
    }
}
