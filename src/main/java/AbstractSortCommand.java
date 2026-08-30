public abstract class AbstractSortCommand implements Command {

    protected static final int NUMBER_FIELD = 0;
    protected static final int MODEL_FIELD = 1;
    protected static final int MILEAGE_FIELD = 2;

    private final String commandName;
    private final String sortName;

    protected AbstractSortCommand(String commandName, String sortName) {
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
            System.out.println("Ошибка: номер поля должен быть числом 0, 1 или 2.");
            printUsage();
            return;
        }

        if (fieldIndex < 0 || fieldIndex > 2) {
            System.out.println("Ошибка: доступные поля: 0 — номер, 1 — модель, 2 — пробег.");
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

        sortAll(data, fieldIndex, ascending);
        System.out.println(sortName + " по полю " + getFieldName(fieldIndex) + " завершена.");
    }

    protected abstract void sortAll(AppData data, int fieldIndex, boolean ascending);

    protected int getIntFieldValue(AppData data, int index, int fieldIndex) {
        Bus bus = data.getBusList().get(index);
        return fieldIndex == NUMBER_FIELD ? bus.getNumber() : bus.getMileage();
    }

    protected String getStringFieldValue(AppData data, int index, int fieldIndex) {
        Bus bus = data.getBusList().get(index);
        return bus.getModel();
    }

    protected int compareFields(AppData data, int index1, int index2, int fieldIndex, boolean ascending) {
        Bus bus1 = data.getBusList().get(index1);
        Bus bus2 = data.getBusList().get(index2);
        
        int comparison;
        
        switch (fieldIndex) {
            case NUMBER_FIELD:
                comparison = Integer.compare(bus1.getNumber(), bus2.getNumber());
                break;
            case MODEL_FIELD:
                comparison = bus1.getModel().compareToIgnoreCase(bus2.getModel());
                break;
            case MILEAGE_FIELD:
                comparison = Integer.compare(bus1.getMileage(), bus2.getMileage());
                break;
            default:
                throw new IllegalArgumentException("Неверный индекс поля: " + fieldIndex);
        }
        
        return ascending ? comparison : -comparison;
    }

    protected String getFieldName(int fieldIndex) {
        switch (fieldIndex) {
            case NUMBER_FIELD:
                return "Номер";
            case MODEL_FIELD:
                return "Модель";
            case MILEAGE_FIELD:
                return "Пробег";
            default:
                return "Неизвестное поле";
        }
    }

    private void printUsage() {
        System.out.println("Формат команды: " + commandName + " <поле> <порядок>");
        System.out.println("Поля: 0 — номер, 1 — модель, 2 — пробег");
        System.out.println("Порядок: asc — возрастание, desc — убывание");
        System.out.println("Пример: " + commandName + " 0 asc");
        System.out.println("Пример: " + commandName + " 1 desc");
        System.out.println("Пример: " + commandName + " 2 asc");
    }
}