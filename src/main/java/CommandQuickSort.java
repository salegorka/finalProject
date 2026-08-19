import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandQuickSort implements Command {
    @Override
    public void execute(String command, AppData data) {
        System.out.println("Быстрая сортировка.");
        int fieldIndex = 0;
        boolean asc = true;
        String[] parts = command.split("\\s+");
        if (parts.length == 3) {
            System.out.println("Заданы параметры");
            try {
                fieldIndex = Integer.parseInt(parts[1]);
                if (fieldIndex >= 3) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Ошибка при чтении первого параметра. Сортировка будет проходить по полю 0.");
            }
            try {
                if (parts[2].equals("asc")) {
                    asc = true;
                } else if (parts[2].equals("desc")) {
                    asc = false;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Ошибка при чтении второго параметра. Сортировка будет проходить возрастанию.");
            }
        } else {
            System.out.println("Выберите поле для сортировки (0,1,2)");
            Scanner scanner = new Scanner(System.in);
            try {
                fieldIndex = scanner.nextInt();
                if (fieldIndex >= 3) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Ошибка при чтении индекса поля. Сортировка будет проходить по полю 0.");
            }
            String inputLine;
            System.out.println("Выберите порядок. По возрастанию asc, по убыванию desc");
            scanner.nextLine(); // очистить буффер
            try {
                inputLine = scanner.nextLine();
                if (inputLine.equals("asc")) {
                    System.out.println("Сортировка по возрастанию");
                } else if (inputLine.equals("desc")) {
                    asc = false;
                    System.out.println("Сортировка по убыванию");
                } else {
                    throw new Exception("Неверный ввод порядка сортировки");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Ошибка при чтении порядка сортировка. Сортировка будет по возрастанию");
            }
        }
        System.out.println("Начинаю сортировку");
        quickSort(data, 0, data.size() - 1, fieldIndex, asc);
        System.out.println("Массив отсортирован");
    }

    public void quickSort(AppData data, int low, int high, int fieldIndex, boolean asc) {
        if (data.size() == 0) {
            return;
        }
        if (low >= high) {
            return;
        }

        int middle = low + (high - low) / 2;

        int i = low;
        int j = high;

        while(i <= j) {
            while(data.compare(i, middle, fieldIndex, asc)) {
                i++;
            }
            while(data.compare(middle, j, fieldIndex, asc)) {
                j--;
            }
            if (i <= j) {
                data.swap(i, j);
                i++;
                j--;
            }
        }

        if (low < j) {
            quickSort(data, low, j, fieldIndex, asc);
        }
        if (high > i) {
            quickSort(data, i, high, fieldIndex, asc);
        }
    }

    //Метод для тестирования сортировки
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
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
        for(int i = 0; i < data.size(); i++) {
            System.out.println(data.getBusList().get(i).toString());
        }
        Command command = new CommandQuickSort();
        command.execute("quickSort", data);
        for(int i = 0; i < data.size(); i++) {
            System.out.println(data.getBusList().get(i).toString());
        }
    }
}
