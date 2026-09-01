public class CommandHelp implements Command {

    @Override
    public void execute(String command, AppData data) {
        System.out.println("Доступные команды:");
        System.out.println("  help          - показать эту справку");
        System.out.println("  exit          - завершить программу");
        System.out.println("  quick [поле] [asc|desc] - быстрая сортировка");
        System.out.println("  bubble [поле] [asc|desc] - сортировка пузырьком");
        System.out.println("  selection [поле] [asc|desc] - сортировка выбором");
        System.out.println("  bubbleSortEven [0|2] [asc|desc] - пузырьковая сортировка только чётных значений");
        System.out.println("  selectionSortEven [0|2] [asc|desc] - сортировка выбором только чётных значений");
        System.out.println("  quickSortEven [0|2] [asc|desc] - быстрая сортировка только чётных значений");
        System.out.println("  save <имя_файла> - сохранить текущий список в указанный файл (добавление)");
        System.out.println("  list          - показать текущий список");
        System.out.println("  add           - ввод данных вручную");
        System.out.println("  random        - заполнить случайными данными");
        System.out.println("  randomswap     - случайно перемешивает список");
        System.out.println("  file          - загрузить из файла (скоро)");
        System.out.println("Поля: 0 - номер, 1 - модель, 2 - пробег");
        System.out.println("Пример: quick 1 asc");
    }
}
