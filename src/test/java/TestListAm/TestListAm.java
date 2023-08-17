package TestListAm;

import am.list.HomePage;
import am.list.Item;
import am.list.ResultPage;
import am.list.exceptions.FilterError;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static java.lang.Thread.sleep;

public class TestListAm extends BaseTestListAm {

    @Test
    void testLastItemIsClickable() {
        HomePage homePage = new HomePage(driver);
        homePage.openPage();
        homePage.changeLanguage("eng");
        ResultPage resultPage = homePage.selectCategory("Electronics", "Notebooks");

        WebElement lastItem = resultPage.getLastItem();
        Assert.assertTrue(isClickable(lastItem));
    }

    @Test
    void testCheckDummyItem() {
        HomePage homePage = new HomePage(driver);
        homePage.openPage();

        homePage.ifExistsChoose("en");

        ResultPage resultPage = homePage.selectCategory("Real Estate", "Apartments");
        resultPage.addFilterRadioButtonSelect("Agency");

        // add dummy element to check if the test works correctly
        resultPage.addDummyItem();

        for (Item item : resultPage.items()) {
            String itemDescription = item.description.getText();

            if (item.label == null)
                throw new FilterError("There are not label in Element: <%s>".formatted(itemDescription));

            softAssert.assertEquals(item.label.getText(), "Agency", "Label is not 'Agency' in Element: <%s>".formatted(itemDescription));
        }

        softAssert.assertAll();
    }

    @Test
    void testCheckMultipleFilters() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.openPage();
        homePage.ifExistsChoose("en");

        ResultPage resultPage = homePage.selectCategory("Electronics", "Notebooks");

        resultPage.addFilterDropDownSelect("Currency", "AMD");
        resultPage.addFilterDropDownSelect("Location", "Kentron");

        resultPage.addFilterInput("Price", "200000", "500000");

        for (Item item : resultPage.items()) {
            String message = "Label is not '%s' in Element: <%s>".formatted("%s", item.description.getText());

            String location = item.location.getText().split(",")[0];

            int price = Integer.parseInt(String.join("", item.price.getText().split(" ")[0].split(",")));
            String  priceCurrency = item.price.getText().split(" ")[1];

            softAssert.assertEquals(priceCurrency, "֏", message);
            softAssert.assertTrue(200000<=price && price<=500000 , message);
            softAssert.assertEquals(location,"Kentron" , message);
        }

        softAssert.assertAll();

    }
}
