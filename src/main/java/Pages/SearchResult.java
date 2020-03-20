package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResult extends BasePage {

    public void chooseTheProduct(String name) throws InterruptedException {
        driver.findElement(By.xpath(name)).click();
        Thread.sleep(10000);
    }

    public SearchResult(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
