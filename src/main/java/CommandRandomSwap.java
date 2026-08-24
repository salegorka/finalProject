
public class CommandRandomSwap implements Command {

    @Override
    public void execute(String command, AppData data) {
        if (data.size() == 0) {
            System.out.println("Список пуст");
            return;
        }
        System.out.println("=== Перемешивание списка ===");
        data.random();  // Просто вызываем метод из AppData
        System.out.println("Список перемешан");
    }
}