package TestListAm;

import am.list.pageobjects.HomePage;
import am.list.components.Item;
import am.list.pageobjects.ResultPage;
import am.list.exceptions.FilterError;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestListAm extends BaseTestListAm {

    @Test(groups = {"itrmChecktest"})
    void testLastItemIsClickable() {
        HomePage homePage = new HomePage(driver).get();
        homePage.changeLanguage("eng");
        ResultPage resultPage = homePage.selectCategory("Electronics", "Notebooks").get();

        WebElement lastItem = resultPage.getLastItem();
        Assert.assertTrue(isClickable(lastItem));
    }

    @Test(groups = {"itrmChecktest", "filtetest"})
    void testCheckDummyItem() {
        HomePage homePage = new HomePage(driver).get();

        ResultPage resultPage = homePage.selectCategory("Real Estate", "Apartments").get();
        resultPage.addFilterRadioButtonSelect("Agency");

        // add dummy element to check if the test works correctly
//        resultPage.addDummyItem();

        for (Item item : resultPage.items()) {
            String itemDescription = item.getDescription();

            try {
                item.getLabel();
            }
            catch (NoSuchElementException e) {
                throw new FilterError("There are not label in Element: <%s>".formatted(itemDescription));
            }

            softAssert.assertEquals(item.getLabel(), "Agency", "Label is not 'Agency' in Element: <%s>".formatted(itemDescription));
        }


        softAssert.assertAll();
    }

    @Test(groups = {"itrmChecktest", "filtetest"})
    void testCheckMultipleFilters() {
        HomePage homePage = new HomePage(driver).get();

        ResultPage resultPage = homePage.selectCategory("Electronics", "Notebooks").get();

        resultPage.addFilterInput("Price", "200000", "500000");
        resultPage.addFilterDropDownSelect("Currency", "AMD");
        resultPage.addFilterDropDownSelect("Location", "Kentron");

        for (Item item : resultPage.items()) {
            String message = "Label is not '%s' in Element: <%s>".formatted("%s", item.getDescription());

            String location = item.getLocation()[0];

            int price = item.getPrice();
            String priceCurrency = item.getCurrency();

            softAssert.assertEquals(priceCurrency, "֏", message);
            softAssert.assertTrue(200000 <= price && price <= 500000, message);
            softAssert.assertEquals(location, "Kentron", message);
        }

        softAssert.assertAll();

    }

    @Test
    void test() {Assert.fail();}

    @Test(groups = {"h1"})
    void test1() {
        System.out.println("test1");
        System.out.println("test group run work correctly");
        Assert.assertTrue(true);
    }
}
