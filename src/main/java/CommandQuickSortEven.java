import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandQuickSortEven implements Command {

    @Override
    public void execute(String command, AppData data) {
        System.out.println("Быстрая сортировка только чётных элементов");
        int fieldIndex = 0;
        boolean asc = true;
        String[] parts = command.split("\\s+");
        if (parts.length == 3) {
            System.out.println("Заданы параметры");
            try {
                fieldIndex = Integer.parseInt(parts[1]);
                if (!(fieldIndex == 2 || fieldIndex == 0)) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Ошибка при чтении первого параметра.");
                this.printUsage();
                return;
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
                System.out.println("Ошибка при чтении второго параметра. ");
                this.printUsage();
                return;
            }
        } else {
            System.out.println("Параметры не заданы.");
            System.out.println("Выберите поле для сортировки (0,2)");
            Scanner scanner = new Scanner(System.in);
            try {
                fieldIndex = scanner.nextInt();
                if (!(fieldIndex == 2 || fieldIndex == 0)) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Ошибка при чтении индекса поля. ");
                this.printUsage();
                return;
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
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Ошибка при чтении порядка сортировка.");
                this.printUsage();
                return;
            }
        }
        System.out.println("Начинаю сортировку");
        quickSortEven(data, 0, data.size() - 1, fieldIndex, asc);
        System.out.println("Массив отсортирован");


    }


    public int findEvenPivot(AppData data, int low, int high, int fieldIndex) {
        int mid = low + (high - low) / 2;

        for (int offset = 0; offset <= (high - low); offset++) {
            if (mid + offset <= high && this.isEven(data, mid + offset, fieldIndex)) {
                return mid + offset;
            }
            if (mid - offset >= low && this.isEven(data, mid - offset, fieldIndex)) {
                return mid - offset;
            }
        }

        return -1;
    }

    public boolean isOdd(AppData data, int index, int fieldIndex) {
        if (fieldIndex == 0) {
            return data.getBusList().get(index).getNumber() % 2 == 1;
        }
        if (fieldIndex == 2) {
            return data.getBusList().get(index).getMileage() % 2 == 1;
        }
        return false;
    }
    public boolean isEven(AppData data, int index, int fieldIndex) {
        if (fieldIndex == 0) {
            return data.getBusList().get(index).getNumber() % 2 == 0;
        }
        if (fieldIndex == 2) {
            return data.getBusList().get(index).getMileage() % 2 == 0;
        }
        return false;
    }

    public void quickSortEven(AppData data, int low, int high, int fieldIndex, boolean asc) {
        if (data.size() == 0) {
            return;
        }

        if (low >= high) {
            return;
        }

        int pivotIndex = findEvenPivot(data, low, high, fieldIndex);
        if (pivotIndex == -1) return;

        int i = low;
        int j = high;

        while (i <= j) {
            while (i <= j && (this.isOdd(data, i, fieldIndex) || data.compare(i, pivotIndex, fieldIndex, asc))) {
                i++;
            }
            while (i <= j && (this.isOdd(data, j, fieldIndex) || data.compare(pivotIndex, j, fieldIndex, asc))) {
                j--;
            }

            if (i <= j) {
                data.swap(i, j);
                i++;
                j--;
            }
        }

        if (low < j) {
            quickSortEven(data, low, j, fieldIndex, asc);
        }
        if (high > i) {
            quickSortEven(data, i, high, fieldIndex, asc);
        }

    }

    public void printUsage() {
        System.out.println("Формат команды quickSortEven <Поле> <Порядок>");
        System.out.println("Поле: 0 - номер или 2 - пробег");
        System.out.println("Порядок: asc - возрастание или desc - убывание");
        System.out.println("Пример команды: quickSortEven 0 asc");
    }

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Bus bus1 = new Bus.Builder().number(12).model("Камаз").mileage(1001).build();
        Bus bus2 = new Bus.Builder().number(5).model("ВАЗ").mileage(5000).build();
        Bus bus3 = new Bus.Builder().number(15).model("Нива").mileage(2001).build();
        Bus bus4 = new Bus.Builder().number(7).model("Белаз").mileage(3000).build();
        Bus bus5 = new Bus.Builder().number(10).model("Мерседес").mileage(2500).build();

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
        Command command = new CommandQuickSortEven();
        command.execute("quickSortEven", data);
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.getBusList().get(i).toString());
        }
    }
}
