// S - данный класс описывает сущность
// O - от данного класса можно создать наследника, который будет описывать конкретный тип продукта, например, BakeryProduct или PersonalHygieneProduct
public class Product {

    private String productName;
    private long productId;
    private double cost;

    public Product (String productName, long productId, double cost) {
        this.productName = productName;
        this.productId = productId;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Товар: " + this.productName + ", " +
                "id: " + this.productId + ", " +
                "цена: " + this.cost;
    }

    public double getCost() {
        return this.cost;
    }

    public String getProductName() {
        return productName;
    }
}