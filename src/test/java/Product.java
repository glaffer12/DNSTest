public class Product {
    public String Name;
    public String Price;
    public String Description;
    public String Guarantee;

    public Integer priceToInt() {
        return Integer.parseInt(Price.replaceAll(" ",""));
    }
}
