package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.CDATASection;

import javax.naming.InsufficientResourcesException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Basket extends BasePage {
    @FindBy(xpath = "//div[@class=\"total-amount__label\"]//*//span[@class=\"price__current\"]")
    WebElement TotalPrice;

    //@FindBy(xpath = "//div[@class=\"cart-items__product-thumbnail cart-items__product-thumbnail_product\"]//*//a[text()=\"Игровая приставка PlayStation 4 Slim Black 1 TB + 3 игры\"]")
    //WebElement PlayStationBasket;

    public void checkGuarantee(String name) {
        String Xpath = "//div[.//a[text()=\""+name+"\"] and @class=\"cart-items__product-thumbnail cart-items__product-thumbnail_product\"]//span[text()=\"Продленная гарантия на 24 мес. \"]";
        Assert.assertTrue(driver.findElement(By.xpath(Xpath)).isDisplayed());
    }


    public void CheckTotalPrice(Integer Price) throws InterruptedException {
        Integer P= Integer.parseInt(TotalPrice.getText().replaceAll(" ",""));
        Assert.assertEquals(P,Price);
    }

    public void CheckPrice(String ProductPrice, String Name) {
        String GuaranteePriceXpath = "//div[.//a[text()=\""+Name+"\"] and @class=\"cart-items__product-thumbnail cart-items__product-thumbnail_product\"]//span[@class=\"list-applied-product-services__item-price\"]";
        String GuaranteePriceRef =driver.findElement(By.xpath(GuaranteePriceXpath)).getText().
                replaceAll("[^0-9]","");
        String ProductPriceXpath = "//div[.//a[text()=\""+Name+"\"] and @class=\"cart-items__product-thumbnail cart-items__product-thumbnail_product\"]//span[@class=\"price__current\"]";
        String ProductPriceRef = driver.findElement(By.xpath(ProductPriceXpath)).getText().
                replaceAll("[^0-9]","");
        Integer ActualProductPrice = Integer.parseInt(GuaranteePriceRef) + Integer.parseInt(ProductPriceRef);
        Integer ExpectedProductPrice = Integer.parseInt(ProductPrice.replaceAll("[^0-9]",""));
        Assert.assertEquals(ActualProductPrice,ExpectedProductPrice);
    }

    public void CheckPriceWithoutGuarantee(String ProductPrice, String Name) {
        String ProductPriceXpath = "//div[.//a[text()=\""+Name+"\"] and @class=\"cart-items__product-thumbnail cart-items__product-thumbnail_product\"]//span[@class=\"price__current\"]";
        String ActualProductPrice = driver.findElement(By.xpath(ProductPriceXpath)).getText();
        Assert.assertEquals(ActualProductPrice,ProductPrice);
    }

    public void DeleteProduct(String Name) {
        String ProductPriceXpath = "//div[.//a[text()=\""+Name+"\"] and @class=\"cart-items__product-thumbnail cart-items__product-thumbnail_product\"]//button[text()=\"Удалить\"]";
        driver.findElement(By.xpath(ProductPriceXpath)).click();
    }

    public void isProductPresent(String Name) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
        WebElement Product = (new WebDriverWait(driver,3))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[.//a[text()=\""+Name+"\"] and @class=\"cart-items__product-thumbnail cart-items__product-thumbnail_product\"]")));
            System.out.println(Name + " is present.");
        }
        catch (TimeoutException e) {
            System.out.println(Name + " is deleted.");
        }
        finally { driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); }
        }

    public void ReturnLastDeletedProduct() {
        driver.findElement(By.xpath("//span[@class=\"restore-last-removed\"]")).click();
    }

    public void IncreaseQuantity(String Name) {
            String ProductPriceXpath = "//div[.//a[text()=\""+Name+"\"] and @class=\"cart-items__product-thumbnail cart-items__product-thumbnail_product\"]//button[@class=\"count-buttons__button count-buttons__button_plus\"]";
            driver.findElement(By.xpath(ProductPriceXpath)).click();
        }

    public void waitWhileTotalPriceChanges(Integer ActualPrice) throws InterruptedException {
        while(ActualPrice.equals(Integer.parseInt(TotalPrice.getText().replaceAll("[^0-9]",""))));
            Thread.sleep(100);
    }

    public Basket(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

}
