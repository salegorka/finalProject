import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandAdd implements Command {

    @Override
    public void execute(String command, AppData data) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n=== Ручной ввод автобусов ===");
        System.out.println("Текущий размер списка: " + data.size() + " автобусов");
        System.out.println("Введите данные в формате: номер,модель,пробег");
        System.out.println("Пример: 10,Камаз,1000");
        System.out.println("Для завершения ввода введите пустую строку или 'done'");
        System.out.println("----------------------------------------");
        System.out.println("Правила валидации:");
        System.out.println("  • Номер: положительное число (до 9999)");
        System.out.println("  • Модель: не может быть пустой");
        System.out.println("  • Пробег: неотрицательное число (до 1,000,000 км)");
        System.out.println("----------------------------------------");
        
        int addedCount = 0;
        int lineNumber = 0;
        
        while (true) {
            lineNumber++;
            System.out.printf("[%d] > ", lineNumber);
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty() || input.equalsIgnoreCase("done")) {
                if (addedCount == 0) {
                    System.out.println("Автобусы не были добавлены.");
                } else {
                    System.out.println("Добавлено " + addedCount + " автобусов.");
                }
                break;
            }
        
            String[] parts = input.split(",");
            if (parts.length != 3) {
                System.out.println("Ошибка: неверный формат. Ожидается: номер,модель,пробег");
                System.out.println("Пример: 10,Камаз,1000");
                continue;
            }
            
            String numberStr = parts[0].trim();
            String modelStr = parts[1].trim();
            String mileageStr = parts[2].trim();
            List<String> errors = new ArrayList<>();
            
            int number = 0; 
            if (numberStr.isEmpty()) {
                errors.add("Номер не может быть пустым.");
            } else {
                try {
                    number = Integer.parseInt(numberStr);
                    if (number < 0) {
                        errors.add("Номер не может быть отрицательным. Получено: " + number);
                    }
                    if (number > 9999) {
                        errors.add("Номер не может быть больше 9999. Получено: " + number);
                    }
                } catch (NumberFormatException e) {
                    errors.add("Номер должен быть целым числом. Получено: '" + numberStr + "'");
                }
            }
            
            if (modelStr.isEmpty()) {
                errors.add("Модель не может быть пустой.");
            }
            
            int mileage = 0; 
            if (mileageStr.isEmpty()) {
                errors.add("Пробег не может быть пустым.");
            } else {
                try {
                    mileage = Integer.parseInt(mileageStr);
                    if (mileage < 0) {
                        errors.add("Пробег не может быть отрицательным. Получено: " + mileage);
                    }
                    if (mileage > 1000000) {
                        errors.add("Пробег не может быть больше 1,000,000. Получено: " + mileage);
                    }
                } catch (NumberFormatException e) {
                    errors.add("Пробег должен быть целым числом. Получено: '" + mileageStr + "'");
                }
            }
            
            if (!errors.isEmpty()) {
                System.out.println("  Ошибки валидации:");
                for (String error : errors) {
                    System.out.println("     • " + error);
                }
                continue;
            }
            
            boolean duplicateFound = false;
            for (Bus existingBus : data.getBusList()) {
                if (existingBus.getNumber() == number) {
                    System.out.println("Автобус с номером " + number + " уже существует:");
                    System.out.println("Существующий: " + existingBus);
                    System.out.print("Заменить существующий? (y/n): ");
                    String answer = scanner.nextLine().trim().toLowerCase();
                    if (answer.equals("y") || answer.equals("yes")) {
                        data.getBusList().remove(existingBus);
                        System.out.println("Существующий автобус удален.");
                        duplicateFound = true;
                        break;
                    } else {
                        System.out.println("Добавление отменено.");
                        duplicateFound = true;
                        break;
                    }
                }
            }
     
            if (duplicateFound) {
                continue;
            }
            
            try {
                Bus bus = new Bus.Builder()
                        .number(number)
                        .model(modelStr)
                        .mileage(mileage)
                        .build();
                
                data.getBusList().add(bus);
                addedCount++;
                System.out.println("Добавлен: " + bus);
                
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка валидации: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка при обработке данных: " + e.getMessage());
            }
        }
        
        System.out.println("----------------------------------------");
        System.out.println("Всего добавлено автобусов: " + addedCount);
        System.out.println("Текущий размер списка: " + data.size() + " автобусов");
        
        if (addedCount > 0) {
            System.out.println("\nСохранить изменения в файл buses.csv? (y/n)");
            String answer = scanner.nextLine().trim().toLowerCase();
            if (answer.equals("y") || answer.equals("yes")) {
                data.saveToCsv();
                System.out.println("Список сохранен в файл buses.csv");
            } else {
                System.out.println("Изменения не сохранены.");
            }
        }
        System.out.println();
    }

    // Метод main для тестирования команды add
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        
        System.out.println("=== Тестирование команды add ===");
        System.out.println("Будет загружен существующий список из файла.");
        System.out.println("Новые автобусы будут добавлены к существующим.");
        System.out.println();
        
        AppData data = new AppData();
        data.loadFromCsv();
        
        System.out.println("Текущий размер списка: " + data.size() + " автобусов");
        
        if (data.size() > 0) {
            CommandList listCommand = new CommandList();
            listCommand.execute("list", data);
        } else {
            System.out.println("Список пуст.");
        }
        
        System.out.println();
        
        CommandAdd command = new CommandAdd();
        command.execute("add", data);
        
        System.out.println("\n=== Итоговый список автобусов ===");
        if (data.getBusList().isEmpty()) {
            System.out.println("Список пуст.");
        } else {
            CommandList listCommand = new CommandList();
            listCommand.execute("list", data);
        }
    }
}