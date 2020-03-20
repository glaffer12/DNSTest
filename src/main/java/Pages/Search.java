package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Base64;

public class Search extends BasePage {

    @FindBy(xpath = "//input[@placeholder = \"Поиск среди более 100 000 товаров\"]")
    WebElement SearchField;

    @FindBy(xpath = "//a[@class=\"ui-link cart-link\"]")
    WebElement Basket;

    public Search(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public void SearchByName(String name) {
        SearchField.click();
        SearchField.sendKeys(name);
        SearchField.sendKeys(Keys.ENTER);
    }

    public void GoToBasket() {
        Basket.click();
    }
}
