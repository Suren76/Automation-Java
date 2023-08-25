package am.list;

import am.list.exceptions.InvalidInput;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Locale;

public class HomePage extends BasePage implements SelectCategoryBar {


    public HomePage(WebDriver driver) {
        super(driver, "");
    }

    public HomePage(WebDriver driver, String endPoint) {
        super(driver, endPoint);
    }

    private void popUpLangChooseMenu(String lang) {
        lang = lang.toLowerCase(Locale.ROOT).substring(0, 2);
        if (!(lang.equals("en") || lang.equals("ru") || lang.equals("am"))) {
            throw new InvalidInput("Invalid input: " + lang);
        }
        shortWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='dlgLangSel']//a[@href='/%s/']".formatted(lang)))).click();
    }

    @Override
    public ResultPage selectCategory(String categoryMenu) {
        return selectCategory(driver, categoryMenu);
    }

    @Override
    public ResultPage selectCategory(String categoryMenu, String subCategory) {
        return selectCategory(driver, categoryMenu, subCategory);
    }

    @Override
    public ResultPage selectCategory(String categoryMenu, String subCategoryTitle, String subCategory) {
        return selectCategory(driver, categoryMenu, subCategoryTitle, subCategory);
    }

    public void ifExistsChoose(String lang) {
        if (shortWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("dlgLangSel"))).size() == 1) {
            popUpLangChooseMenu(lang);
        }
    }

}

