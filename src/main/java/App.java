import java.util.Scanner;

public class App {
    public static void main() {
        AppData appData = new AppData();
        CommandRouter router = new CommandRouter();
        start(appData);
        System.out.println("Консольная утилита для сортировки объектов help");
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            router.setCurrentCommand(command);
            router.currentCommand.execute(command, appData);
        }

    }

    public static void start(AppData appData) {
        //метод запускается при старте программы
    }

    public static void end(AppData appData) {
        //метод запускается при закрытии программы
    }
}
