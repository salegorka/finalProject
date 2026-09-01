public class CommandLoadFromFile implements Command {
    @Override
    public void execute(String command, AppData data) {
        int firstSpace = command.indexOf(' ');
        if (firstSpace == -1) {
            System.out.println("Использование: loadFromFile <имя_файла>");
            return;
        }
        String fileName = command.substring(firstSpace + 1).trim();
        if (fileName.isEmpty()) {
            System.out.println("Имя файла не может быть пустым.");
            return;
        }
        data.loadFromFile(fileName);
    }
}