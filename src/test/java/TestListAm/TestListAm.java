package TestListAm;

import am.list.HomePage;
import am.list.ResultPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

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
}
