import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Тесты для команды CommandQuickSort.
 */
public class CommandQuickSortTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        testNumberAscending();
        testNumberDescending();
        testModelAscending();
        testModelDescending();
        testMileageAscending();
        testMileageDescending();
        testEmptyList();
        testSingleElement();
        testAlreadySorted();
        testReverseSorted();
        testDuplicates();
        testLargeList();
        printResult();
    }

    /**
     * Проверка сортировки номеров по возрастанию
     */
    private static void testNumberAscending() {
        runTest(
                "QuickSort: номер по возрастанию",
                new Runnable() {
                    @Override
                    public void run() {

                        AppData data = createData();

                        CommandQuickSort command = new CommandQuickSort();

                        command.execute("quickSort 0 asc", data);

                        assertNumbers(data, 1, 2, 3, 4, 5);
                    }
                }
        );
    }

    /**
     * Проверка сортировки номеров по убыванию
     */
    private static void testNumberDescending() {
        runTest(
                "QuickSort: номер по убыванию",
                new Runnable() {
                    @Override
                    public void run() {

                        AppData data = createData();

                        CommandQuickSort command = new CommandQuickSort();

                        command.execute("quickSort 0 desc", data);

                        assertNumbers(data, 5, 4, 3, 2, 1);
                    }
                }
        );
    }

    /**
     * Проверка сортировки моделей по возрастанию
     */
    private static void testModelAscending() {
        runTest(
                "QuickSort: модель по возрастанию",
                new Runnable() {
                    @Override
                    public void run() {

                        AppData data = createData();

                        CommandQuickSort command = new CommandQuickSort();

                        command.execute("quickSort 1 asc", data);

                        assertModels(data, "A", "B", "C", "D", "E");
                    }
                }
        );
    }

    /**
     * Проверка сортировки моделей по убыванию
     */
    private static void testModelDescending() {
        runTest(
                "QuickSort: модель по убыванию",
                new Runnable() {
                    @Override
                    public void run() {

                        AppData data = createData();

                        CommandQuickSort command = new CommandQuickSort();

                        command.execute("quickSort 1 desc", data);

                        assertModels(data, "E", "D", "C", "B", "A");
                    }
                }
        );
    }

    /**
     * Проверка сортировки пробега по возрастанию
     */
    private static void testMileageAscending() {
        runTest(
                "QuickSort: пробег по возрастанию",
                new Runnable() {
                    @Override
                    public void run() {

                        AppData data = createData();

                        CommandQuickSort command = new CommandQuickSort();

                        command.execute("quickSort 2 asc", data);

                        assertMileages(data, 1000, 2000, 2500, 3000, 5000);
                    }
                }
        );
    }

    /**
     * Проверка сортировки пробега по убыванию
     */
    private static void testMileageDescending() {
        runTest(
                "QuickSort: пробег по убыванию",
                new Runnable() {
                    @Override
                    public void run() {

                        AppData data = createData();

                        CommandQuickSort command = new CommandQuickSort();

                        command.execute("quickSort 2 desc", data);

                        assertMileages(data, 5000, 3000, 2500, 2000, 1000);
                    }
                }
        );
    }

    /**
     * Проверка пустого списка
     */
    private static void testEmptyList() {
        runTest(
                "QuickSort: пустой список",
                new Runnable() {
                    @Override
                    public void run() {

                        List<Bus> BusList = new ArrayList<Bus>();

                        AppData data = new AppData(BusList);

                        CommandQuickSort command = new CommandQuickSort();

                        command.execute("quickSort 0 asc", data);

                        if (data.size() != 0) {
                            throw new AssertionError("Список должен остаться пустым");
                        }
                    }
                }
        );
    }

    /**
     * Проверка списка с одним элементом
     */
    private static void testSingleElement() {
        runTest(
                "QuickSort: один элемент",
                new Runnable() {
                    @Override
                    public void run() {

                        List<Bus> BusList = new ArrayList<Bus>();

                        BusList.add(createBus(10, "D", 1000));

                        AppData data = new AppData(BusList);

                        CommandQuickSort command = new CommandQuickSort();

                        command.execute("quickSort 0 asc", data);

                        assertNumbers(data, 10);
                    }
                }
        );
    }

    /**
     * Проверка уже отсортированного списка
     */
    private static void testAlreadySorted() {
        runTest(
                "QuickSort: уже отсортированный список",
                new Runnable() {
                    @Override
                    public void run() {

                        List<Bus> BusList = new ArrayList<Bus>();

                        BusList.add(createBus(1, "A", 100));
                        BusList.add(createBus(2, "B", 200));
                        BusList.add(createBus(3, "C", 300));
                        BusList.add(createBus(4, "D", 400));
                        BusList.add(createBus(5, "E", 500));

                        AppData data = new AppData(BusList);

                        CommandQuickSort command = new CommandQuickSort();

                        command.execute("quickSort 0 asc", data);

                        assertNumbers(data, 1, 2, 3, 4, 5);
                    }
                }
        );
    }

    /**
     * Проверка списка отсортированного в обратном порядке
     */
    private static void testReverseSorted() {
        runTest(
                "QuickSort: обратный порядок",
                new Runnable() {
                    @Override
                    public void run() {

                        List<Bus> BusList = new ArrayList<Bus>();

                        BusList.add(createBus(5, "E", 500));
                        BusList.add(createBus(4, "D", 400));
                        BusList.add(createBus(3, "C", 300));
                        BusList.add(createBus(2, "B", 200));
                        BusList.add(createBus(1, "A", 100));

                        AppData data = new AppData(BusList);

                        CommandQuickSort command = new CommandQuickSort();

                        command.execute("quickSort 0 asc", data);

                        assertNumbers(data, 1, 2, 3, 4, 5);
                    }
                }
        );
    }

    /**
     * Проверка повторяющихся значений
     */
    private static void testDuplicates() {
        runTest(
                "QuickSort: одинаковые значения",
                new Runnable() {
                    @Override
                    public void run() {

                        List<Bus> BusList = new ArrayList<Bus>();

                        BusList.add(createBus(1, "A", 100));
                        BusList.add(createBus(2, "B", 200));
                        BusList.add(createBus(1, "C", 300));
                        BusList.add(createBus(2, "D", 400));

                        AppData data = new AppData(BusList);

                        CommandQuickSort command = new CommandQuickSort();

                        command.execute("quickSort 0 asc", data);

                        assertNumbers(data, 1, 1, 2, 2);
                    }
                }
        );
    }

    /**
     * Проверка QuickSort на большом списке
     */
    private static void testLargeList() {
        runTest(
                "QuickSort: большой список",
                new Runnable() {
                    @Override
                    public void run() {

                        List<Bus> BusList = new ArrayList<Bus>();

                        for (int i = 100; i >= 1; i--) {
                            BusList.add(createBus(i, "Bus" + i, i * 100));
                        }

                        AppData data = new AppData(BusList);

                        CommandQuickSort command = new CommandQuickSort();

                        command.execute("quickSort 0 asc", data);

                        for (int i = 0; i < 100; i++) {

                            int actual = data.getBusList().get(i).getNumber();

                            int expected = i + 1;

                            if (actual != expected) {
                                throw new AssertionError("Позиция " + i + ": ожидалось " + expected + ", получено " + actual);
                            }
                        }
                    }
                }
        );
    }

    /**
     * Создаёт пять автобусов
     */
    private static AppData createData() {

        List<Bus> BusList = new ArrayList<Bus>();

        BusList.add(createBus(1, "A", 1000));

        BusList.add(createBus(2, "B", 5000));

        BusList.add(createBus(3, "C", 2000));

        BusList.add(createBus(4, "D", 3000));

        BusList.add(createBus(5, "E", 2500));

        return new AppData(BusList);
    }

    private static Bus createBus(int number, String model, int mileage) {

        return new Bus.Builder()
                .number(number)
                .model(model)
                .mileage(mileage)
                .build();
    }

    /**
     * Проверяет номера автобусов
     */
    private static void assertNumbers(AppData data, int... expected) {

        if (data.size() != expected.length) {

            throw new AssertionError("Количество элементов: ожидалось " + expected.length + ", получено " + data.size());
        }

        for (int i = 0; i < expected.length; i++) {

            int actual = data.getBusList().get(i).getNumber();

            if (actual != expected[i]) {
                throw new AssertionError("Позиция " + i + ": ожидалось " + expected[i] + ", получено " + actual);
            }
        }
    }

    /**
     * Проверяет модели автобусов
     */
    private static void assertModels(AppData data, String... expected) {

        if (data.size() != expected.length) {
            throw new AssertionError("Количество элементов: ожидалось " + expected.length + ", получено " + data.size());
        }

        for (int i = 0; i < expected.length; i++) {

            String actual = data.getBusList().get(i).getModel();

            if (!expected[i].equals(actual)) {
                throw new AssertionError("Позиция " + i + ": ожидалось " + expected[i] + ", получено " + actual);
            }
        }
    }

    /**
     * Проверяет пробег автобусов
     */
    private static void assertMileages(AppData data, int... expected) {

        if (data.size() != expected.length) {
            throw new AssertionError("Количество элементов: ожидалось " + expected.length + ", получено " + data.size());
        }

        for (int i = 0; i < expected.length; i++) {

            int actual = data.getBusList().get(i).getMileage();

            if (actual != expected[i]) {
                throw new AssertionError("Позиция " + i + ": ожидалось " + expected[i] + ", получено " + actual);
            }
        }
    }

    private static void runTest(String name, Runnable test) {
        try {

            test.run();

            passed++;

            System.out.println("[PASS] " + name);

        } catch (Throwable e) {

            failed++;

            System.out.println("[FAIL] " + name);

            System.out.println(e.getMessage());
        }
    }

    private static void printResult() {

        System.out.println();

        System.out.println("Всего тестов: " + (passed + failed));

        System.out.println("Пройдено: " + passed);

        System.out.println("Провалено: " + failed);

        if (failed == 0) {
            System.out.println("Тесты пройдены успешно");
        }
    }
}