import java.io.*;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class AppData {
    private List<Bus> busList;
    private static final String CSV_FILE = "buses.csv";

    public AppData() {
        this.busList = new ArrayList<>();
    }

    public AppData(List<Bus> busList) {
        this.busList = busList;
    }

    // метод возращает размер массива
    public int size() {
        return this.busList.size();
    }

    //метод для сравнения

    public boolean compare(int i, int j, int index, boolean asc) throws IndexOutOfBoundsException {
        if (asc) {
            if (index == 0) {
                return this.busList.get(i).getNumber() < this.busList.get(j).getNumber();
            } else if (index == 1) {
                return this.busList.get(i).getModel().compareTo(this.busList.get(j).getModel()) < 0;
            } else if (index == 2) {
                return this.busList.get(i).getMileage() < this.busList.get(j).getMileage();
            } else {
                throw new IndexOutOfBoundsException("Неверный индекс поля для сортировки");
            }
        } else {
            if (index == 0) {
                return this.busList.get(i).getNumber() > this.busList.get(j).getNumber();
            } else if (index == 1) {
                return this.busList.get(i).getModel().compareTo(this.busList.get(j).getModel()) > 0;
            } else if (index == 2) {
                return this.busList.get(i).getMileage() > this.busList.get(j).getMileage();
            } else {
                throw new IndexOutOfBoundsException("Неверный индекс поля для сортировки");
            }
        }
    }

    //метод меняет местами элементы i и j
    public void swap(int i, int j) {
        Bus temp = this.busList.get(i);
        this.busList.set(i, this.busList.get(j));
        this.busList.set(j, temp);
    }

    public void setDatalist(List<Bus> datalist) {
        this.busList = datalist;
    }

    public void randomSwap() {
        if (busList.isEmpty()) {
            System.out.println("Список пуст");
            return;
        }

        Random random = new Random();

        for (int i = busList.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            swap(i, j);
        }
    }

    //метод пермешивает массив в случайном порядке
    public void random() {
        Random random = new Random();

        for (int i = busList.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);


            Bus temp = busList.get(i);
            busList.set(i, busList.get(j));
            busList.set(j, temp);
        }
    }

    public List<Bus> getBusList() {
        return this.busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }

    //загружаем список автобусов из csv-файла
    public void loadFromCsv() {
        List<Bus> loaded = new ArrayList<>();
        if (!Files.exists(Paths.get(CSV_FILE))) {
            System.out.println("CSV файл не найден. Начинаем с пустого списка.");
            this.setBusList(loaded);
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(CSV_FILE))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    System.err.println("Пропущена строка " + lineNumber + ": неверный формат (" + line + ")");
                    continue;
                }
                try {
                    int number = Integer.parseInt(parts[0].trim());
                    String model = parts[1].trim();
                    int mileage = Integer.parseInt(parts[2].trim());
                    Bus bus = new Bus.Builder()
                            .number(number)
                            .model(model)
                            .mileage(mileage)
                            .build();
                    loaded.add(bus);
                } catch (NumberFormatException e) {
                    System.err.println("Пропущена строка " + lineNumber + ": неверное число");
                } catch (IllegalArgumentException e) {
                    System.err.println("Пропущена строка " + lineNumber + ": " + e.getMessage());
                }
            }
            this.setBusList(loaded);
            System.out.println("Загружено " + loaded.size() + " автобусов из " + CSV_FILE);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            this.setBusList(new ArrayList<>());
        }
    }

    // сохраняем текущий список в csv-файл
    public void saveToCsv() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(CSV_FILE))) {
            for (Bus bus : this.busList) {
                String line = bus.getNumber() + "," + bus.getModel() + "," + bus.getMileage();
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Сохранено " + this.busList.size() + " автобусов в " + CSV_FILE);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения файла: " + e.getMessage());
        }
    }
}
