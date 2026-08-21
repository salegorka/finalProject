public class CommandBubbleSortEven extends AbstractEvenSortCommand {

    public CommandBubbleSortEven() {
        super("bubbleSortEven", "Пузырьковая сортировка");
    }

    @Override
    protected void sortEven(AppData data, int fieldIndex, boolean ascending) {
        boolean swapped;

        do {
            swapped = false;
            int previousEvenIndex = -1;

            for (int currentIndex = 0; currentIndex < data.size(); currentIndex++) {
                if (!isEven(data, currentIndex, fieldIndex)) {
                    continue;
                }

                if (previousEvenIndex >= 0
                        && data.compare(currentIndex, previousEvenIndex, fieldIndex, ascending)) {
                    data.swap(currentIndex, previousEvenIndex);
                    swapped = true;
                }

                previousEvenIndex = currentIndex;
            }
        } while (swapped);
    }
}
