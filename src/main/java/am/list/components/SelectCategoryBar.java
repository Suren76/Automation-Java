package am.list.components;

import am.list.pageobjects.ResultPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SelectCategoryBar extends BaseComponent {
    private WebDriver driver;

    public SelectCategoryBar(WebDriver driver, WebElement element) {
        super(element);
        this.driver = driver;
    }

    public SelectCategoryBar(WebDriver driver) {
        super(driver.findElement(By.xpath("div[@id='menu']/div/div")));
        this.driver = driver;
    }

    public WebDriverWait shortWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public ResultPage selectCategory(String categoryMenu) {
        WebElement globalCategoryItem = driver.findElement(By.xpath("./a[text()='%s']/..".formatted(categoryMenu)));

        globalCategoryItem.click();
        return new ResultPage(driver);
    }

    public ResultPage selectCategory(String categoryMenu, String subCategory) {
        WebElement globalCategoryItem = driver.findElement(By.xpath("./a[text()='%s']/..".formatted(categoryMenu)));
        new Actions(driver).moveToElement(globalCategoryItem).build().perform();

        WebElement categoryToSelect = globalCategoryItem.findElement(By.xpath(".//a[text()='%s']".formatted(subCategory)));
        shortWait().until(ExpectedConditions.elementToBeClickable(categoryToSelect));

        categoryToSelect.click();
        return new ResultPage(driver);
    }

    public ResultPage selectCategory(String categoryMenu, String subCategoryTitle, String subCategory) {
        WebElement globalCategoryItem = driver.findElement(By.xpath("./a[text()='%s']/..".formatted(categoryMenu)));
        new Actions(driver).moveToElement(globalCategoryItem).build().perform();
        WebElement subCategoryTitleToSelect = null;

        int index = 0;

        for (WebElement item : globalCategoryItem.findElements(By.xpath(".//div[@class='pane']/b"))) {
            if (item.getText().equals(subCategoryTitle)) {
                index = globalCategoryItem.findElements(By.xpath(".//div[@class='pane']/b")).indexOf(item);
                subCategoryTitleToSelect = globalCategoryItem.findElements(By.xpath(".//div[@class='pane']/div")).get(index);
                break;
            }
        }

        WebElement categoryToSelect = subCategoryTitleToSelect.findElement(By.xpath(".//a[text()='%s']".formatted(subCategory)));
        shortWait().until(ExpectedConditions.elementToBeClickable(categoryToSelect));

        categoryToSelect.click();
        return new ResultPage(driver);
    }
}
