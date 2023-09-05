package am.list.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class Item extends BaseComponent {

    @FindBy(xpath = ".//div[@class='clabel']")
    private WebElement label;

    @FindBy(xpath = ".//img[@src='//s.list.am']")
    private WebElement image;

    @FindBy(xpath = ".//div[@class='p']")
    private WebElement price;

    @FindBy(xpath = ".//div[@class='lbls']/span")
    private List<WebElement> labels;

    @FindBy(xpath = ".//div[@class='rv']")
    private WebElement viewed;

    @FindBy(xpath = ".//div[@class='l' or contains(@class, 'l3')]")
    private WebElement description;

    @FindBy(xpath = ".//div[@class='at']")
    private WebElement location;


    public Item(WebElement elem) {
        super(elem);
    }

    public String getLabel() {
        return label.getText();
    }

    public String getImage() {
        return image.getText();
    }

    public String getPrice() {
        return price.getText();
    }

    public List<String> getLabels() {
        List<String> list = new ArrayList<>();
        for (WebElement l: labels) {
            list.add(l.getText());
        }

        return list;
    }

    public String getViewed() {
        return viewed.getText();
    }

    public String getDescription() {
        return description.getText();
    }

    public String getLocation() {
        return location.getText();
    }
}
