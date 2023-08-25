package am.list.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Item extends BaseComponent {

    public WebElement label;
    public WebElement image;
    public WebElement price;
    public List<WebElement> labels;
    public WebElement viewed;
    public WebElement description;
    public WebElement location;


    public Item(WebElement elem) {
        setLabel(elem);
        setImage(elem);
        setPrice(elem);
        setLabels(elem);
        setViewed(elem);
        setDescription(elem);
        setLocation(elem);
    }

    private void setLabel(WebElement elem) {
        List<WebElement> tmpElem = elem.findElements(By.xpath(".//div[@class='clabel']"));
        this.label = tmpElem.size() > 0 ? tmpElem.get(0) : null;
    }

    private void setImage(WebElement elem) {
        List<WebElement> tmpElem = elem.findElements(By.xpath(".//img[@src='//s.list.am']"));
        this.image = tmpElem.size() > 0 ? tmpElem.get(0) : null;
    }

    private void setPrice(WebElement elem) {
        List<WebElement> tmpElem = elem.findElements(By.xpath(".//div[@class='p']"));
        this.price = tmpElem.size() > 0 ? tmpElem.get(0) : null;
    }

    private void setLabels(WebElement elem) {
        List<WebElement> tmpElem = elem.findElements(By.xpath(".//div[@class='lbls']/span"));
        this.labels = tmpElem.size() > 0 ? tmpElem : null;
    }

    private void setViewed(WebElement elem) {
        List<WebElement> tmpElem = elem.findElements(By.xpath(".//div[@class='rv']"));
        this.viewed = tmpElem.size() > 0 ? tmpElem.get(0) : null;
    }

    private void setDescription(WebElement elem) {
        List<WebElement> tmpElem = elem.findElements(By.xpath(".//div[@class='l']"));
        this.description = tmpElem.size() > 0 ? tmpElem.get(0) : null;
    }

    private void setLocation(WebElement elem) {
        List<WebElement> tmpElem = elem.findElements(By.xpath(".//div[@class='at']"));
        this.location = tmpElem.size() > 0 ? tmpElem.get(0) : null;
    }

}
