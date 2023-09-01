package am.list.pageobjects;

import am.list.components.SelectCategoryBar;
import am.list.exceptions.InvalidInput;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Locale;

public class HomePage extends BasePage<HomePage> {

    By itemsXpath = By.xpath("//a[contains(@href, '/item/')]");
    SelectCategoryBar selectCategoryBar = new SelectCategoryBar(driver);

    public HomePage(WebDriver driver) {
        super(driver, "/");
    }

    private void popUpLangChooseMenu(String lang) {
        lang = lang.toLowerCase(Locale.ROOT).substring(0, 2);
        if (!(lang.equals("en") || lang.equals("ru") || lang.equals("am"))) {
            throw new InvalidInput("Invalid input: " + lang);
        }
        shortWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='dlgLangSel']//a[@href='/%s/']".formatted(lang)))).click();
    }

    public ResultPage selectCategory(String categoryMenu) {
        return selectCategoryBar.selectCategory(categoryMenu);
    }

    public ResultPage selectCategory(String categoryMenu, String subCategory) {
        return selectCategoryBar.selectCategory(categoryMenu, subCategory);
    }

    public ResultPage selectCategory(String categoryMenu, String subCategoryTitle, String subCategory) {
        return selectCategoryBar.selectCategory(categoryMenu, subCategoryTitle, subCategory);
    }

    @Override
    public void changeLanguage(String lang) {
        super.changeLanguage(lang);
    }

    public void ifExistsChoose(String lang) {
        try {
            if (shortWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("dlgLangSel"))).size() == 1) {
                popUpLangChooseMenu(lang);
            }
        } catch (TimeoutException e) {
            return;
        }
    }

    @Override
    protected void load() {
        openPage();
        ifExistsChoose("en");
    }

    @Override
    protected void isLoaded() throws Error {
        if (!(driver.getCurrentUrl().contains(BASE_PAGE_URL + endPoint))) {
            throw new Error("The page is not loaded! \n context: {%s}".formatted(driver.getCurrentUrl() + ", " + BASE_PAGE_URL + endPoint));
        }
        if (!(driver.findElements(itemsXpath).size() > 0)) {
            throw new Error("The page is not loaded!");
        }
    }

}

