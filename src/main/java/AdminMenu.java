import java.util.List;
import java.util.Scanner;

// S - данный класс выполняет только функции, связанные с вводом и выводом информации
public class AdminMenu {

    Scanner scanner;

    public AdminMenu() {
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("1. Обновить текущий список продуктов");
        System.out.println("2. Показать список продуктов");
        System.out.println("3. Сортировка продуктов по стоимости");
        System.out.println("4. Сортировка продуктов по алфавиту");
        System.out.println("5. Выход из программы");
        System.out.println("6. Добавить в список два экземпляра класса-наследника");
    }

    public String waitForAnswer() {
        return scanner.nextLine();
    }

    // DRY - наиболее часто повторяющиеся действия вынесены в отдельные методы
    public void successfulRefreshMessage() {
        System.out.println("Список успешно обновлён!");
    }

    // DRY
    public void listAcceptErrorMessage() {
        System.out.println("Не удаётся получить доступ к списку товаров! Пожалуйста, обновите список.");
    }

    // DRY
    public void inputErrorMessage() {
        System.out.println("Произошла ошибка ввода, пожалуйста, повторите попытку!");
    }

    // DRY
    public void printProductList(List<Product> list) {
        // Magic
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.print("\n");
    }

    public void addBunsMessage() {
        System.out.println("Две булочки успешно добавлены в список товаров!");
    }
}