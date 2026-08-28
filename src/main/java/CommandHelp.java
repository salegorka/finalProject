public class CommandHelp implements Command {

    @Override
    public void execute(String command, AppData data) {
        System.out.println("Доступные команды:");
        System.out.println("  help          - показать эту справку");
        System.out.println("  exit          - завершить программу");
        System.out.println("  quick [поле] [asc|desc] - быстрая сортировка");
        System.out.println("  save <имя_файла> - сохранить текущий список в указанный файл (добавление)");
        System.out.println("  bubbleSortEven [0|2] [asc|desc] - пузырьковая сортировка только чётных значений");
        System.out.println("  selectionSortEven [0|2] [asc|desc] - сортировка выбором только чётных значений");
        System.out.println("  bubble        - сортировка пузырьком (скоро)");
        System.out.println("  list          - показать текущий список (скоро)");
        System.out.println("  random        - заполнить случайными данными (скоро)");
        System.out.println("  file          - загрузить из файла (скоро)");
        System.out.println("Поля: 0 - номер, 1 - модель, 2 - пробег");
        System.out.println("Пример: quick 1 asc");
    }
}
