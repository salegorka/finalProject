import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CommandBubbleSort extends AbstractSortCommand {

    public CommandBubbleSort() {
        super("bubbleSort", "Сортировка пузырьком");
    }

    @Override
    protected void sortAll(AppData data, int fieldIndex, boolean ascending) {
        boolean swapped;
        
        do {
            swapped = false;
            
            for (int i = 0; i < data.size() - 1; i++) {
                if (data.compare(i + 1, i, fieldIndex, ascending)) {
                    data.swap(i, i + 1);
                    swapped = true;
                }
            }
        } while (swapped);
    }

     // Метод для тестирования сортировки пузырьком
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        
        // Создаем тестовые данные
        Bus bus1 = new Bus.Builder().number(10).model("Камаз").mileage(1000).build();
        Bus bus2 = new Bus.Builder().number(5).model("ВАЗ").mileage(5000).build();
        Bus bus3 = new Bus.Builder().number(15).model("Нива").mileage(2000).build();
        Bus bus4 = new Bus.Builder().number(7).model("Белаз").mileage(3000).build();
        Bus bus5 = new Bus.Builder().number(12).model("Мерседес").mileage(2500).build();

        List<Bus> busList = new ArrayList<>();
        busList.add(bus1);
        busList.add(bus2);
        busList.add(bus3);
        busList.add(bus4);
        busList.add(bus5);
        
        AppData data = new AppData(busList);
        
        System.out.println("Исходный список");
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.getBusList().get(i).toString());
        }
        
        Command command = new CommandBubbleSort();
        command.execute("bubbleSort 1 desc", data); 
        
        System.out.println("\nОтсортированный список");
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.getBusList().get(i).toString());
        }
    }

}
