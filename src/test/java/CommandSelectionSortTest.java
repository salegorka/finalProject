import java.util.ArrayList;
import java.util.List;

public class CommandSelectionSortTest {

    private static int passedTests = 0;

    public static void main(String[] args) {
        testSortByNumberAscending();
        testSortByNumberDescending();
        testSortByModelAscending();
        testSortByModelDescending();
        testSortByMileageAscending();
        testSortByMileageDescending();
        testEmptyList();
        testSingleElement();

        System.out.println("Все тесты сортировки выбором пройдены: " + passedTests + "/8");
    }

    private static void testSortByNumberAscending() {
        AppData data = createTestData();
        execute(data, "selection 0 asc");
        assertOrder(data, 10, 20, 30);
        pass("сортировка по номеру по возрастанию");
    }

    private static void testSortByNumberDescending() {
        AppData data = createTestData();
        execute(data, "selection 0 desc");
        assertOrder(data, 30, 20, 10);
        pass("сортировка по номеру по убыванию");
    }

    private static void testSortByModelAscending() {
        AppData data = createTestData();
        execute(data, "selection 1 asc");
        assertModels(data, "Alpha", "Beta", "Gamma");
        pass("сортировка по модели по возрастанию");
    }

    private static void testSortByModelDescending() {
        AppData data = createTestData();
        execute(data, "selection 1 desc");
        assertModels(data, "Gamma", "Beta", "Alpha");
        pass("сортировка по модели по убыванию");
    }

    private static void testSortByMileageAscending() {
        AppData data = createTestData();
        execute(data, "selection 2 asc");
        assertMileages(data, 1_000, 2_000, 3_000);
        pass("сортировка по пробегу по возрастанию");
    }

    private static void testSortByMileageDescending() {
        AppData data = createTestData();
        execute(data, "selection 2 desc");
        assertMileages(data, 3_000, 2_000, 1_000);
        pass("сортировка по пробегу по убыванию");
    }

    private static void testEmptyList() {
        AppData data = new AppData();
        execute(data, "selection 0 asc");
        assertEquals(0, data.size(), "Пустой список должен остаться пустым");
        pass("сортировка пустого списка");
    }

    private static void testSingleElement() {
        AppData data = new AppData();
        data.setDatalist(new ArrayList<>(List.of(createBus(7, "Single", 700))));
        execute(data, "selection 0 asc");
        assertOrder(data, 7);
        pass("сортировка списка из одного элемента");
    }

    private static AppData createTestData() {
        AppData data = new AppData();
        List<Bus> buses = new ArrayList<>();
        buses.add(createBus(30, "Beta", 1_000));
        buses.add(createBus(10, "Gamma", 3_000));
        buses.add(createBus(20, "Alpha", 2_000));
        data.setDatalist(buses);
        return data;
    }

    private static Bus createBus(int number, String model, int mileage) {
        return new Bus.Builder()
                .number(number)
                .model(model)
                .mileage(mileage)
                .build();
    }

    private static void execute(AppData data, String command) {
        new CommandSelectionSort().execute(command, data);
    }

    private static void assertOrder(AppData data, int... expectedNumbers) {
        assertEquals(expectedNumbers.length, data.size(), "Неверный размер списка");
        for (int i = 0; i < expectedNumbers.length; i++) {
            assertEquals(expectedNumbers[i], data.getBusList().get(i).getNumber(),
                    "Неверный номер автобуса на позиции " + i);
        }
    }

    private static void assertModels(AppData data, String... expectedModels) {
        for (int i = 0; i < expectedModels.length; i++) {
            String actual = data.getBusList().get(i).getModel();
            if (!expectedModels[i].equals(actual)) {
                throw new AssertionError("Неверная модель на позиции " + i
                        + ": ожидалась " + expectedModels[i] + ", получена " + actual);
            }
        }
    }

    private static void assertMileages(AppData data, int... expectedMileages) {
        for (int i = 0; i < expectedMileages.length; i++) {
            assertEquals(expectedMileages[i], data.getBusList().get(i).getMileage(),
                    "Неверный пробег на позиции " + i);
        }
    }

    private static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + ": ожидалось " + expected + ", получено " + actual);
        }
    }

    private static void pass(String testName) {
        passedTests++;
        System.out.println("Тест пройден: " + testName);
    }
}
