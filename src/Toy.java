/**
 * Класс представляющий игрушку
 */
public class Toy {
    private int id;
    private String name;
    private int quantity;
    private int frequency;


    /**
     * Конструктор
     * @param id идентификатор игрушки
     * @param name наименование игрушки
     * @param quantity количество игрушек в магазине
     * @param frequency частота выпадения
     */
    public Toy(int id, String name, int quantity, int frequency) {
        this.id = id;
        this.name = name;
        this.quantity = Math.max(quantity, 0); // Убедиться, что количество не отрицательное
        this.frequency = Math.max(frequency, 0); // Убедиться, что частота не отрицательная
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
