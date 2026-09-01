import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        AppData appData = new AppData();
        CommandRouter router = new CommandRouter();
        start(appData);
        System.out.println("Консольная утилита для сортировки объектов. Справка - help.");

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            String command = scanner.nextLine().trim();
            if (command.isEmpty()) continue;

            if (command.equalsIgnoreCase("exit")) {
                running = false;
                end(appData);
                System.out.println("Программа завершена");
                break;
            }

            router.setCurrentCommand(command);
            if (router.currentCommand != null) {
                router.currentCommand.execute(command, appData);
            } else {
                System.out.println("Неизвестная команда. Введите help.");
            }
        }

        scanner.close();
    }

    public static void start(AppData appData) {
        appData.loadFromCsv();
    }

    public static void end(AppData appData) {
        appData.saveToCsv();
    }
}
