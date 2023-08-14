package TestListAm;

import am.list.HomePage;
import am.list.ResultPage;
import am.list.exceptions.FilterError;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestListAm extends BaseTestListAm {

    @Test
    void testLastItemIsClickable() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.changeLanguage("eng");
        ResultPage resultPage = homePage.selectCategory("Electronics", "Notebooks");

        WebElement lastItem = resultPage.getLastItem();
        Assert.assertTrue(isClickable(lastItem));
    }

    @Test
    void testCheckDummyItem() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        if (homePage.shortWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("dlgLangSel"))).size() == 1)
            homePage.popUpLangChoseMenu("en");

        ResultPage resultPage = homePage.selectCategory("Real Estate", "Apartments");
        resultPage.addFilterRadioButtonSelect("Agency");

        // add dummy element to check if the test works correctly
//        resultPage.addDummyItem(driver.findElement(By.xpath("//div[@id='tp']//div[@class='gl']")));

        List<WebElement> items = driver.findElements(By.xpath("//a[contains(@href, '/en/item/')]"));

        for (WebElement item : items) {
            String itemDescription = item.findElement(By.xpath(".//div[@class='l' or contains(@class, 'l ')]")).getText();
            String label;

            try {
                label = item.findElement(By.xpath(".//div[@class='clabel']")).getText();
            } catch (NoSuchElementException e) {
                throw new FilterError("There are not label in Element: %s".formatted(itemDescription));
            }

            Assert.assertEquals(label, "Agency", "Label is not 'Agency' in Element: %s".formatted(itemDescription));
        }

    }

}
