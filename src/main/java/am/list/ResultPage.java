package am.list;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResultPage extends BasePage {

    public ResultPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getLastItem() {
        List<WebElement> items = driver.findElements(By.xpath("//a[contains(@href, '/en/item/')]"));
        return items.get(items.size() - 1);
    }

}
