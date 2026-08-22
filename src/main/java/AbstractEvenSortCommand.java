public abstract class AbstractEvenSortCommand implements Command {

    protected static final int NUMBER_FIELD = 0;
    protected static final int MILEAGE_FIELD = 2;

    private final String commandName;
    private final String sortName;

    protected AbstractEvenSortCommand(String commandName, String sortName) {
        this.commandName = commandName;
        this.sortName = sortName;
    }

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
            System.out.println("Ошибка: номер поля должен быть числом 0 или 2.");
            printUsage();
            return;
        }

        if (fieldIndex != NUMBER_FIELD && fieldIndex != MILEAGE_FIELD) {
            System.out.println("Ошибка: чётность можно проверить только у номера (0) или пробега (2).");
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

        sortEven(data, fieldIndex, ascending);
        System.out.println(sortName + " чётных значений завершена.");
    }

    protected abstract void sortEven(AppData data, int fieldIndex, boolean ascending);

    protected boolean isEven(AppData data, int index, int fieldIndex) {
        Bus bus = data.getBusList().get(index);
        int value = fieldIndex == NUMBER_FIELD ? bus.getNumber() : bus.getMileage();
        return value % 2 == 0;
    }

    private void printUsage() {
        System.out.println("Формат команды: " + commandName + " <поле> <порядок>");
        System.out.println("Поля: 0 — номер, 2 — пробег");
        System.out.println("Порядок: asc — возрастание, desc — убывание");
        System.out.println("Пример: " + commandName + " 0 asc");
    }
}
