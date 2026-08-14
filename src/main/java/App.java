import java.util.Scanner;

public class App {
    public static void main() {
        AppData appData = new AppData();
        CommandRouter router = new CommandRouter();
        start(appData);
        System.out.println("Консольное приложение для сортировки объектов. Для получения списка комманд введите help");
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            router.setCurrentCommand(command);
            router.currentCommand.execute(command, appData);
        }

    }

    public static void start(AppData appData) {
        //метод загружает данные в appData при старте программы
    }

    public static void end(AppData appData) {
        //метод сохраняет данные в файле при завершении программы
    }
}
