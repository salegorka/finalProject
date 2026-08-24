import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CommandRandom implements Command {
    private final Random random;
    private final Scanner scanner;

    private static final String[] BUS_MODELS = {
            "Mercedes-Benz Sprinter", "Volkswagen Crafter", "Ford Transit",
            "Iveco Daily", "MAN Lion's City", "Scania Citywide",
            "Volvo 7900", "Setra S 415", "Neoplan Tourliner",
            "Ikarus 250", "LiAZ-5292", "PAZ-3205",
            "KAMAZ-6282", "MAZ-203", "Yutong ZK6122"
    };

    public CommandRandom() {
        this.random = new Random();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute(String command, AppData data) {
        System.out.println("=== Random generation of buses ===");

        // Получаем количество автобусов
        int count = getNumberOfBuses();

        // Генерируем случайные автобусы
        List<Bus> buses = generateRandomBuses(count);

        // Перемешиваем список
        data.random();

        // Показываем результат
        System.out.println("\nGenerated " + count + " random buses:");
        printBuses(buses);
    }

    private int getNumberOfBuses() {
        int count = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter number of buses to generate (1-50): ");
            try {
                String input = scanner.nextLine().trim();
                count = Integer.parseInt(input);

                if (count >= 1 && count <= 50) {
                    validInput = true;
                } else {
                    System.out.println("Error: number must be between 1 and 50");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: please enter a valid number");
            }
        }

        return count;
    }

    private List<Bus> generateRandomBuses(int count) {
        List<Bus> buses = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Bus bus = generateRandomBus();
            buses.add(bus);
        }

        return buses;
    }

    private Bus generateRandomBus() {
        // Генерация номера
        int number = random.nextInt(999999) + 1;

        // Генерация модели
        String model = BUS_MODELS[random.nextInt(BUS_MODELS.length)];

        // Генерация пробега
        int mileage = random.nextInt(1000001);

        // Создание автобуса
        return new Bus.Builder()
                .number(number)
                .model(model)
                .mileage(mileage)
                .build();
    }

    private void printBuses(List<Bus> buses) {
        System.out.println("-".repeat(60));
        for (int i = 0; i < buses.size(); i++) {
            System.out.printf("%3d. %s%n", (i + 1), buses.get(i));
        }
        System.out.println("-".repeat(60));
    }
}