import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CommandList implements Command {

    @Override
    public void execute(String command, AppData data) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        
        List<Bus> busList = data.getBusList();
        
        if (busList.isEmpty()) {
            System.out.println("Список автобусов пуст.");
            return;
        }
        
        System.out.println("\n=== Список автобусов ===");
        System.out.println("┌────┬──────────┬─────────────────┬──────────┐");
        System.out.println("│ №  │  Номер   │     Модель      │ Пробег   │");
        System.out.println("├────┼──────────┼─────────────────┼──────────┤");
        
        for (int i = 0; i < busList.size(); i++) {
            Bus bus = busList.get(i);
            System.out.printf("│ %-2d │ %-8d │ %-15s │ %-8d │%n", 
                              i + 1,
                              bus.getNumber(),
                              bus.getModel(),
                              bus.getMileage()
            );
        }
        
        System.out.println("└────┴──────────┴─────────────────┴──────────┘");
        System.out.println("Всего: " + busList.size() + " автобусов\n");
    }

    // Метод для тестирования команды list
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        
        List<Bus> busList = new ArrayList<>();
        busList.add(new Bus.Builder().number(10).model("Камаз").mileage(1000).build());
        busList.add(new Bus.Builder().number(5).model("ВАЗ").mileage(5000).build());
        busList.add(new Bus.Builder().number(15).model("Нива").mileage(2000).build());
        busList.add(new Bus.Builder().number(7).model("Белаз").mileage(3000).build());
        busList.add(new Bus.Builder().number(12).model("Мерседес").mileage(2500).build());
        
        AppData data = new AppData(busList);
        
        System.out.println("Тестирование команды list:");
        System.out.println("Команда: list");
        System.out.println("----------------------------------------");
        
        CommandList command = new CommandList();
        command.execute("list", data);
    }
}