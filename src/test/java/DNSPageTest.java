import Pages.Basket;
import Pages.ProductCard;
import Pages.Search;
import Pages.SearchResult;
import org.junit.Test;
import org.openqa.selenium.By;

public class DNSPageTest extends BaseTest {

    @Test
    public void testDNS() throws InterruptedException {
        driver.get(baseUrl+"/");

        Search search = new Search(driver);
        search.SearchByName("playstation 4");

        SearchResult sr = new SearchResult(driver);
        sr.chooseTheProduct("//a[text()=\"Игровая приставка PlayStation 4 Slim Black 1 TB + 3 игры\"][1]");
        ProductCard Card = new ProductCard(driver);
        Product PlayStation = new Product();
        PlayStation.Price = Card.GetThePrice();
        PlayStation.Name = Card.GetName();
        Card.SelectGuarantee(2);
        Card.waitWhilePriceChanges(PlayStation.Price);
        PlayStation.Price = Card.GetThePrice();
        Card.addToBasket();

        search.SearchByName("Detroit");

        Product DetroitGame = new Product();
        DetroitGame.Price = Card.GetThePrice();
        DetroitGame.Name = Card.GetName();
        Card.addToBasket();

        search.GoToBasket();

        Basket basket = new Basket(driver);
        String sk = PlayStation.Price.replaceAll(" ","");
        basket.CheckTotalPrice(PlayStation.priceToInt()+DetroitGame.priceToInt());
        basket.checkGuarantee(PlayStation.Name);
        basket.CheckPrice(PlayStation.Price,PlayStation.Name);
        basket.CheckPriceWithoutGuarantee(DetroitGame.Price,DetroitGame.Name);
        basket.DeleteProduct(DetroitGame.Name);
        basket.waitWhileTotalPriceChanges(DetroitGame.priceToInt() + PlayStation.priceToInt());
        basket.isProductPresent(DetroitGame.Name);
        basket.CheckTotalPrice(PlayStation.priceToInt());
        basket.IncreaseQuantity(PlayStation.Name);
        basket.waitWhileTotalPriceChanges(PlayStation.priceToInt());
        basket.IncreaseQuantity(PlayStation.Name);
        basket.waitWhileTotalPriceChanges(PlayStation.priceToInt()*2);
        basket.CheckTotalPrice((PlayStation.priceToInt())*3);
        basket.ReturnLastDeletedProduct();
        basket.waitWhileTotalPriceChanges(PlayStation.priceToInt()*3);
        basket.isProductPresent(DetroitGame.Name);

    }
}
