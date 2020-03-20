package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;

public class ProductCard extends BasePage {

    @FindBy(xpath = "//span[@class=\"current-price-value\"]")
    WebElement Price;

    @FindBy(xpath = "//select[@class=\"form-control select\"]")
    WebElement Guarantee;

    @FindBy(xpath = "//button[@data-original-title = \"Добавить в корзину\"]")
    WebElement AddToBasket;

    @FindBy(xpath = "//h1[@class=\"page-title price-item-title\"]")
    WebElement Name;

    public ProductCard(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void SelectGuarantee(int index) throws InterruptedException {
        Select select = new Select(Guarantee);
        select.selectByIndex(index);
    }

    public void waitWhilePriceChanges(String ActualPrice) throws InterruptedException {
        while(ActualPrice.equals(Price.getText()))
            Thread.sleep(100);
    }

    public void addToBasket() {
        AddToBasket.click();
    }

    public String GetThePrice() {
        return Price.getText();
    }

    public String GetName() {
        return Name.getText();
    }

}
