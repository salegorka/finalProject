public class CommandSaveToFile implements Command {
    @Override
    public void execute(String command, AppData data) {
        // Извлекаем имя файла после команды (учитываем пробелы в имени)
        int firstSpace = command.indexOf(' ');
        if (firstSpace == -1) {
            System.out.println("Использование: save <имя_файла>");
            return;
        }
        String fileName = command.substring(firstSpace + 1).trim();
        if (fileName.isEmpty()) {
            System.out.println("Имя файла не может быть пустым.");
            return;
        }
        data.appendToFile(fileName);
    }
}