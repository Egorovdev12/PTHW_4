import java.util.Scanner;

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
    }

    public String waitForAnswer() {
        return scanner.nextLine();
    }
}