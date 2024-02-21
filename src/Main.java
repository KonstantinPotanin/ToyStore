import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Добавляем игрушки в магазин
        ToyStore toyStore = new ToyStore();
        toyStore.addToyInStore(new Toy(1, "Doll", 5, 1));
        toyStore.addToyInStore(new Toy(2, "Car", 3, 5));
        toyStore.addToyInStore(new Toy(3, "Bear", 4, 99));

        // Изменяем частоту выпадения игрушки "Doll"
        toyStore.updateFrequency(1, 15);

        // Изменяем количество игрушки "Doll"
        toyStore.setQuantity(1, 6);

        // Запрос на количество разыгранных игрушек
        Scanner scanner = new Scanner(System.in);
        int numDraws;
        while (true) {
            System.out.println("Сколько игрушек будем разыгрывать?");
            if (scanner.hasNextInt()) {
                numDraws = scanner.nextInt();
                break;
            } else {
                System.out.println("Пожалуйста, введите целое число.");
                scanner.next(); // Очистка буфера сканнера
            }
        }

        // Запись разыгранных игрушек в txt файл
        try {
            toyStore.toyRaffle(numDraws);
            toyStore.savePrizesToFile("prizes.txt");
            System.out.println("Розыгрыш успешно завершен. Призы сохранены в файле prizes.txt.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
