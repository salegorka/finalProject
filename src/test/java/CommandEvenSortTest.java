import java.util.ArrayList;
import java.util.List;

public class CommandEvenSortTest {

    private static int passedTests = 0;

    public static void main(String[] args) {
        testBubbleByNumberAscending();
        testBubbleByNumberDescending();
        testBubbleByMileageAscending();
        testBubbleByMileageDescending();
        testSelectionByNumberAscending();
        testSelectionByNumberDescending();
        testSelectionByMileageAscending();
        testSelectionByMileageDescending();
        testBubbleRejectsModelField();
        testSelectionRejectsModelField();
        testBubbleEmptyList();
        testSelectionEmptyList();
        testRouterFindsBubbleSortEven();
        testRouterFindsSelectionSortEven();

        System.out.println("Все тесты сортировки чётных значений пройдены: " + passedTests + "/14");
    }

    private static void testBubbleByNumberAscending() {
        AppData data = createTestData();
        new CommandBubbleSortEven().execute("bubbleSortEven 0 asc", data);
        assertNumbers(data, 2, 5, 4, 7, 8);
        pass("bubbleSortEven: номера по возрастанию");
    }

    private static void testBubbleByNumberDescending() {
        AppData data = createTestData();
        new CommandBubbleSortEven().execute("bubbleSortEven 0 desc", data);
        assertNumbers(data, 8, 5, 4, 7, 2);
        pass("bubbleSortEven: номера по убыванию");
    }

    private static void testBubbleByMileageAscending() {
        AppData data = createTestData();
        new CommandBubbleSortEven().execute("bubbleSortEven 2 asc", data);
        assertMileages(data, 2, 9, 6, 11, 14);
        pass("bubbleSortEven: пробег по возрастанию");
    }

    private static void testBubbleByMileageDescending() {
        AppData data = createTestData();
        new CommandBubbleSortEven().execute("bubbleSortEven 2 desc", data);
        assertMileages(data, 14, 9, 6, 11, 2);
        pass("bubbleSortEven: пробег по убыванию");
    }

    private static void testSelectionByNumberAscending() {
        AppData data = createTestData();
        new CommandSelectionSortEven().execute("selectionSortEven 0 asc", data);
        assertNumbers(data, 2, 5, 4, 7, 8);
        pass("selectionSortEven: номера по возрастанию");
    }

    private static void testSelectionByNumberDescending() {
        AppData data = createTestData();
        new CommandSelectionSortEven().execute("selectionSortEven 0 desc", data);
        assertNumbers(data, 8, 5, 4, 7, 2);
        pass("selectionSortEven: номера по убыванию");
    }

    private static void testSelectionByMileageAscending() {
        AppData data = createTestData();
        new CommandSelectionSortEven().execute("selectionSortEven 2 asc", data);
        assertMileages(data, 2, 9, 6, 11, 14);
        pass("selectionSortEven: пробег по возрастанию");
    }

    private static void testSelectionByMileageDescending() {
        AppData data = createTestData();
        new CommandSelectionSortEven().execute("selectionSortEven 2 desc", data);
        assertMileages(data, 14, 9, 6, 11, 2);
        pass("selectionSortEven: пробег по убыванию");
    }

    private static void testBubbleRejectsModelField() {
        AppData data = createTestData();
        new CommandBubbleSortEven().execute("bubbleSortEven 1 asc", data);
        assertNumbers(data, 8, 5, 2, 7, 4);
        pass("bubbleSortEven: строковое поле отклонено");
    }

    private static void testSelectionRejectsModelField() {
        AppData data = createTestData();
        new CommandSelectionSortEven().execute("selectionSortEven 1 asc", data);
        assertNumbers(data, 8, 5, 2, 7, 4);
        pass("selectionSortEven: строковое поле отклонено");
    }

    private static void testBubbleEmptyList() {
        AppData data = new AppData();
        new CommandBubbleSortEven().execute("bubbleSortEven 0 asc", data);
        assertEquals(0, data.size(), "Пустой список должен остаться пустым");
        pass("bubbleSortEven: пустой список");
    }

    private static void testSelectionEmptyList() {
        AppData data = new AppData();
        new CommandSelectionSortEven().execute("selectionSortEven 0 asc", data);
        assertEquals(0, data.size(), "Пустой список должен остаться пустым");
        pass("selectionSortEven: пустой список");
    }

    private static void testRouterFindsBubbleSortEven() {
        CommandRouter router = new CommandRouter();
        router.setCurrentCommand("bubbleSortEven 0 asc");
        if (!(router.currentCommand instanceof CommandBubbleSortEven)) {
            throw new AssertionError("CommandRouter не нашёл команду bubbleSortEven");
        }
        pass("CommandRouter подключает bubbleSortEven");
    }

    private static void testRouterFindsSelectionSortEven() {
        CommandRouter router = new CommandRouter();
        router.setCurrentCommand("selectionSortEven 2 desc");
        if (!(router.currentCommand instanceof CommandSelectionSortEven)) {
            throw new AssertionError("CommandRouter не нашёл команду selectionSortEven");
        }
        pass("CommandRouter подключает selectionSortEven");
    }

    private static AppData createTestData() {
        List<Bus> buses = new ArrayList<>();
        buses.add(createBus(8, "Alpha", 14));
        buses.add(createBus(5, "Beta", 9));
        buses.add(createBus(2, "Gamma", 6));
        buses.add(createBus(7, "Delta", 11));
        buses.add(createBus(4, "Epsilon", 2));
        return new AppData(buses);
    }

    private static Bus createBus(int number, String model, int mileage) {
        return new Bus.Builder()
                .number(number)
                .model(model)
                .mileage(mileage)
                .build();
    }

    private static void assertNumbers(AppData data, int... expected) {
        assertEquals(expected.length, data.size(), "Неверный размер списка");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], data.getBusList().get(i).getNumber(),
                    "Неверный номер автобуса на позиции " + i);
        }
    }

    private static void assertMileages(AppData data, int... expected) {
        assertEquals(expected.length, data.size(), "Неверный размер списка");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], data.getBusList().get(i).getMileage(),
                    "Неверный пробег на позиции " + i);
        }
    }

    private static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + ": ожидалось " + expected + ", получено " + actual);
        }
    }

    private static void pass(String name) {
        passedTests++;
        System.out.println("Тест пройден: " + name);
    }
}
