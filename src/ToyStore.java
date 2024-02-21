import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс представляющий магазин игрушек
 */
public class ToyStore {
    protected List<Toy>toys;
    private List<Toy> prizeFund;

    /**
     * Конструктор
     * toys список игрушек в магазине
     * prizeFund призовой фонд
     */
    public ToyStore() {
        toys = new ArrayList<>();
        prizeFund = new ArrayList<>();
    }

    /**
     * Метод добавляет игрушку в магазин
     */
    public void addToyInStore(Toy toy) {
        toys.add(toy);
    }

    /**
     * Метод изменяет частоту выпадения игрушки
     */
    public void updateFrequency(int toyId, int toyFrequency) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setFrequency(Math.max(toyFrequency, 0)); // Убедиться, что частота не отрицательная
                return;
            }
        }
        throw new IllegalArgumentException("Игрушка с указанным ID не найдена.");
    }

    /**
     * Метод изменяет количество игрушек в магазине
     */
    public void setQuantity(int toyId, int quantity) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setQuantity(Math.max(quantity, 0)); // Убедиться, что количество не отрицательное
                return;
            }
        }
        throw new IllegalArgumentException("Игрушка с указанным ID не найдена.");
    }

    /**
     * Метод описывает логику для проведения розыгрыша игрушек
     */
    public void toyRaffle(int numberOfToys) {
        Random random = new Random();
        int totalToysAvailable = toys.stream().mapToInt(Toy::getQuantity).sum(); // Общее количество доступных игрушек
        if (numberOfToys > totalToysAvailable) {
            throw new IllegalArgumentException("Ошибка: запрошенное количество игрушек больше, чем имеющееся в магазине.");
        }

        for (int i = 0; i < numberOfToys; i++) {
            int totalFrequency = toys.stream().filter(toy -> toy.getQuantity() > 0).mapToInt(Toy::getFrequency).sum(); // Общая сумма частот доступных игрушек
            int randomNumber = random.nextInt(totalFrequency);
            int cumulativeFrequency = 0;
            Toy selectedToy = null;

            for (Toy toy : toys) {
                if (toy.getQuantity() > 0) { // Учитываем только доступные игрушки
                    cumulativeFrequency += toy.getFrequency();
                    if (randomNumber < cumulativeFrequency) {
                        selectedToy = toy;
                        break;
                    }
                }
            }

            if (selectedToy != null) {
                selectedToy.setQuantity(selectedToy.getQuantity() - 1);
                prizeFund.add(selectedToy);
            }
        }
    }

    /**
     * Метод для сохранения выигранных игрушек в файл
     */
    public void savePrizesToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Toy toy : prizeFund) {
                writer.write("ID: " + toy.getId() + ", Name: " + toy.getName() + "\n");
            }
            writer.write("End of draw");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
