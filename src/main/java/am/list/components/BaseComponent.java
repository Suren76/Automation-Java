package am.list.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class BaseComponent {
    protected WebElement superElement;

    BaseComponent(WebElement element) {
        superElement = element;
        PageFactory.initElements(superElement, this);
    }

}
