import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Executor {

    private final String PATH_TO_DATABASE = "database.json";
    private AdminMenu adminMenu = new AdminMenu();
    private List<Product> productsList;

    public List<Product> refreshProductListFromSource() throws IOException, org.json.simple.parser.ParseException {
        Object object = new JSONParser().parse(new FileReader(PATH_TO_DATABASE));
        JSONObject jo = (JSONObject) object;
        JSONArray jsonArray = (JSONArray) jo.get("products");
        Iterator productsIter = jsonArray.iterator();

        List<Product> refreshedProductList = new LinkedList<>();

        while (productsIter.hasNext()) {
            JSONObject currentJsonObj = (JSONObject) productsIter.next();
            refreshedProductList.add(new Product(
                    (String)currentJsonObj.get("productName"),
                    (long)currentJsonObj.get("productId"),
                    (double)currentJsonObj.get("cost")));
        }
        return refreshedProductList;
    }

    public void whatToDo() throws IOException, org.json.simple.parser.ParseException {
        String answer = adminMenu.waitForAnswer();

        switch (answer) {
            case "1":
                productsList = new LinkedList<>(refreshProductListFromSource());
                System.out.println("Список успешно обновлён!");
                break;

            case "2":
                if (this.productsList == null){
                    System.out.println("Не удаётся получить доступ к списку товаров! Пожалуйста, обновите список.");
                    break;
                }
                else {
                    for (int i = 0; i < this.productsList.size(); i++) {
                        System.out.println(productsList.get(i));
                    }
                    System.out.print("\n");
                }
                break;

            case "3":
                if (this.productsList == null){
                    System.out.println("Не удаётся получить доступ к списку товаров! Пожалуйста, обновите список.");
                    break;
                }
                else {
                    this.productsList = this.productsList.stream()
                            .sorted((p1, p2) -> (int) (p1.getCost() - p2.getCost()))
                            .toList();
                }
                break;

            case "4":
                if (this.productsList == null){
                    System.out.println("Не удаётся получить доступ к списку товаров! Пожалуйста, обновите список.");
                    break;
                }
                else {
                    this.productsList = this.productsList.stream()
                            .sorted(Comparator.comparing(Product::getProductName))
                            .toList();
                }
                break;

            case "5":
                System.exit(0);
                break;

            default:
                System.out.println("Произошла ошибка ввода, пожалуйста, повторите попытку!");
                break;
        }
    }

    public void start() throws IOException, org.json.simple.parser.ParseException {
        while (true) {
            adminMenu.showMenu();
            whatToDo();
        }
    }
}