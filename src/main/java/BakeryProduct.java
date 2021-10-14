// L - данный класс демонстрирует принцип замены барбары лисков
public class BakeryProduct extends Product {

    protected enum BreadKind {
        WHITE, WHEAT, WHOLE_GRAIN, RYE
    }

    private BreadKind breadType;

    public BakeryProduct(String productName, long productId, double cost, BreadKind bk) {
        super(productName, productId, cost);
        this.breadType = bk;
    }

    public void setKind(BreadKind kind) {
        this.breadType = kind;
    }

    public BreadKind getKind() {
        return this.breadType;
    }

    @Override
    public String toString() {
        return super.toString() + ", тесто: " + this.breadType;
    }
}