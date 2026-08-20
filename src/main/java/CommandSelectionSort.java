public class CommandSelectionSort implements Command {

    private static final int NUMBER_FIELD = 0;
    private static final int MODEL_FIELD = 1;
    private static final int MILEAGE_FIELD = 2;

    @Override
    public void execute(String command, AppData data) {
        String[] parameters = command.trim().split("\\s+");

        if (parameters.length != 3) {
            printUsage();
            return;
        }

        int fieldIndex;
        try {
            fieldIndex = Integer.parseInt(parameters[1]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: номер поля должен быть числом 0, 1 или 2.");
            printUsage();
            return;
        }

        if (fieldIndex < NUMBER_FIELD || fieldIndex > MILEAGE_FIELD) {
            System.out.println("Ошибка: доступные поля для сортировки — 0, 1 или 2.");
            printUsage();
            return;
        }

        boolean ascending;
        if ("asc".equalsIgnoreCase(parameters[2])) {
            ascending = true;
        } else if ("desc".equalsIgnoreCase(parameters[2])) {
            ascending = false;
        } else {
            System.out.println("Ошибка: порядок сортировки должен быть asc или desc.");
            printUsage();
            return;
        }

        selectionSort(data, fieldIndex, ascending);
        System.out.println("Сортировка выбором завершена.");
    }

    public void selectionSort(AppData data, int fieldIndex, boolean ascending) {
        for (int currentIndex = 0; currentIndex < data.size() - 1; currentIndex++) {
            int selectedIndex = currentIndex;

            for (int checkedIndex = currentIndex + 1; checkedIndex < data.size(); checkedIndex++) {
                if (data.compare(checkedIndex, selectedIndex, fieldIndex, ascending)) {
                    selectedIndex = checkedIndex;
                }
            }

            if (selectedIndex != currentIndex) {
                data.swap(currentIndex, selectedIndex);
            }
        }
    }

    private void printUsage() {
        System.out.println("Формат команды: selection <поле> <порядок>");
        System.out.println("Поля: 0 — номер, 1 — модель, 2 — пробег");
        System.out.println("Порядок: asc — возрастание, desc — убывание");
        System.out.println("Пример: selection 2 asc");
    }
}
