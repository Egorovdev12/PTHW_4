import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// S - данный класс занимается вычислительными работами
public class Executor {

    private final String PATH_TO_DATABASE = "database.json";
    private final String KEY_FOR_PRODUCT_ARRAY = "products";
    private final String KEY_PRODUCT_NAME = "productName";
    private final String KEY_PRODUCT_ID = "productId";
    private final String KEY_COST = "cost";
    private AdminMenu adminMenu = new AdminMenu();
    private List<Product> productsList;

    // S - ответственность за каждую операцию дилегирована в отдельные методы
    public List<Product> refreshProductListFromSource() throws IOException, org.json.simple.parser.ParseException {
        // Magic
        Object object = new JSONParser().parse(new FileReader(PATH_TO_DATABASE));
        JSONObject jo = (JSONObject) object;
        JSONArray jsonArray = (JSONArray) jo.get(KEY_FOR_PRODUCT_ARRAY);
        Iterator productsIter = jsonArray.iterator();

        List<Product> refreshedProductList = new LinkedList<>();

        while (productsIter.hasNext()) {
            JSONObject currentJsonObj = (JSONObject) productsIter.next();
            // Magic
            refreshedProductList.add(new Product(
                    (String)currentJsonObj.get(KEY_PRODUCT_NAME),
                    (long)currentJsonObj.get(KEY_PRODUCT_ID),
                    (double)currentJsonObj.get(KEY_COST)));
        }
        return refreshedProductList;
    }

    private void refreshProductList() throws IOException, org.json.simple.parser.ParseException {
        productsList = new LinkedList<>(refreshProductListFromSource());
        adminMenu.successfulRefreshMessage();
    }

    private void showActualProductList() {
        if (this.productsList == null) {
            adminMenu.listAcceptErrorMessage();
            return;
        }
        else {
          adminMenu.printProductList(this.productsList);
        }
    }

    private void costSort() {
        if (this.productsList == null){
            adminMenu.listAcceptErrorMessage();
            return;
        }
        else {
            this.productsList = this.productsList.stream()
                    .sorted((p1, p2) -> (int) (p1.getCost() - p2.getCost()))
                    .toList();
        }
    }

    private void alphabetSort() {
        if (this.productsList == null){
            adminMenu.listAcceptErrorMessage();
            return;
        }
        else {
            this.productsList = this.productsList.stream()
                    .sorted(Comparator.comparing(Product::getProductName))
                    .toList();
        }
    }

    // Данный метод демонстрирует принцип замены Барбары Лисков - наследник имеет возможность логически сыграть роль своего предка
    private void addBuns() {
        if (this.productsList == null) {
            productsList = new LinkedList<>();
        }
        Product bun1 = new BakeryProduct("Big tasty bun", 117536, 56.49, BakeryProduct.BreadKind.WHITE);
        Product bun2 = new BakeryProduct("Fondant bun", 117537, 97.99, BakeryProduct.BreadKind.WHEAT);
        productsList.add(bun1);
        productsList.add(bun2);
    }

    private void whatToDo() throws IOException, org.json.simple.parser.ParseException {
        String answer = adminMenu.waitForAnswer();

        switch (answer) {
            case "1": refreshProductList(); break;

            case "2": showActualProductList(); break;

            case "3": costSort(); break;

            case "4": alphabetSort(); break;

            case "5": System.exit(0); break;

            case "6": addBuns(); adminMenu.addBunsMessage(); break;

            default: adminMenu.inputErrorMessage(); break;
        }
    }

    public void start() throws IOException, org.json.simple.parser.ParseException {
        while (true) {
            adminMenu.showMenu();
            whatToDo();
        }
    }
}